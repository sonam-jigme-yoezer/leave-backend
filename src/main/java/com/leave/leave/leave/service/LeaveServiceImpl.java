package com.leave.leave.leave.service;

import com.leave.leave.leave.dto.*;
import com.leave.leave.leave.mapper.LeaveApplicationMapper;
import com.leave.leave.leave.model.*;
import com.leave.leave.leave.repository.*;
import com.leave.leave.utility.ErrorCodes;
import com.leave.leave.utility.ErrorResponse;
import com.leave.leave.utility.LeaveCalculator;
import com.leave.leave.utility.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    private LeaveApplicationMapper leaveApplicationMapper;

    @Autowired
    private LeaveApprovalRepository leaveApprovalRepository;

    @Autowired
    private LeaveAllocationRepository leaveAllocationRepository;

    @Autowired
    private LeaveCalendarRepository leaveCalendarRepository;

    @Autowired
    private LeaveCalculator calculator;


    @Override
    public ResponseEntity<?> submitLeaveApplication(LeaveApplicationRequest request) {
        try {
            // 1. Find employee by CID
            Employee employee = employeeRepository.findByCidNumber(request.getCid())
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found for CID: " + request.getCid()));

            // 2. Find leave type by leave name
            LeaveType leaveType = leaveTypeRepository.findByLeaveName(request.getLeaveName())
                    .orElseThrow(() -> new IllegalArgumentException("Leave type not found for name: " + request.getLeaveName()));

            // 3. Get allocation year from leave fromDate
            short allocationYear = (short) request.getFromDate().getYear();

            // 4. Find LeaveAllocation for employee and year
            LeaveAllocation allocation = leaveAllocationRepository
                    .findByEmployeeEmpIdAndAllocationYear(employee.getEmpId(), allocationYear)
                    .orElseThrow(() -> new IllegalArgumentException("Leave allocation not found for employee in year " + allocationYear));

            // 5. Map request to LeaveApplication entity and set allocation
            LeaveApplication application = leaveApplicationMapper.toEntity(request, employee, leaveType);
            application.setAllocation(allocation);

            // 6. Save leave application first (so applied_date is recorded)
            leaveApplicationRepository.save(application);

            // 7. Now get latest applied_date for the employee after saving
            LocalDate latestAppliedDate = leaveApplicationRepository.findLatestAppliedDateByEmployee(employee.getEmpId())
                    .orElse(request.getFromDate());

            // 8. Calculate first and last day of that month
            LocalDate monthStart = latestAppliedDate.withDayOfMonth(1);
            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

            // 9. Calculate working days excluding Sundays & holidays for that month
            int workingDaysInMonth = calculator.countLeaveDaysExcludingWeekendsAndHolidays(employee, monthStart, monthEnd);

            // Debug log
            System.out.println("Working days for employee " + employee.getEmpId() + " in month " + monthStart.getMonth() + ": " + workingDaysInMonth);

            // 10. Update no_of_working_days in allocation and save
            allocation.setNoOfWorkingDays(BigDecimal.valueOf(workingDaysInMonth));
            leaveAllocationRepository.save(allocation);

            // 11. Return success with working days info
            return ResponseEntity.ok(new SuccessResponse<>("success", "Leave applied successfully. Working days in month: " + workingDaysInMonth));
        } catch (IllegalArgumentException e) {
            return ErrorResponse.buildErrorResponse(ErrorCodes.INVALID_INPUT_DATA, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Log stack trace for debugging
            return ErrorResponse.buildErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR, "Failed to apply leave: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getRecentLeaves(String mode, LocalDate current) {
        try {
            List<LeaveApplicationResponseDTO> results;
            LocalDate today = current != null ? current : LocalDate.now();

            if ("current".equalsIgnoreCase(mode)) {
                // Check today's leave applications using applied_date
                results = leaveApplicationRepository.findApplicationsByAppliedDate(today);

            } else if ("all".equalsIgnoreCase(mode)) {
                // Last 5 days using from_date
                LocalDate startDate = today.minusDays(4);
                results = leaveApplicationRepository.findRecentLeaveApplications(startDate, today);

            } else {
                return ErrorResponse.buildErrorResponse(
                        ErrorCodes.INVALID_INPUT_DATA,
                        "Invalid mode. Use either 'current' or 'all'."
                );
            }

            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ErrorResponse.buildErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR, "Failed to fetch leave data.");
        }

    }

    @Override
    public ResponseEntity<?> getApprovalsByEmployee(UUID empId) {
        try {
            List<LeaveApprovalResponseDTO> approvals = leaveApprovalRepository.findApprovalsByEmployee(empId);
            return ResponseEntity.ok(approvals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch leave approvals");
        }
    }

    @Override
    public ResponseEntity<?> approveLeave(UUID applicationId, boolean approved, String reason) {
        LeaveApplication app = leaveApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        LeaveApproval approval = new LeaveApproval();
        approval.setApplication(app);
        approval.setApprovalLevel((short) 1);
        approval.setStatus(approved ? "Approved" : "Rejected");
        approval.setRejectionReason(approved ? null : reason);
        approval.setApprovalDate(OffsetDateTime.now());
        approval.setCreatedDate(OffsetDateTime.now());
        approval.setModifiedDate(OffsetDateTime.now());
        leaveApprovalRepository.save(approval);

        if (approved) {
            // ✅ Calculate valid leave days (excluding weekends & holidays)
            int countedDays = calculator.countLeaveDaysExcludingWeekendsAndHolidays(
                    app.getEmployee(), app.getFromDate(), app.getToDate());

            BigDecimal totalDays = BigDecimal.valueOf(countedDays);
            app.setTotalDays(totalDays);

            LeaveAllocation alloc = leaveAllocationRepository
                    .findByEmployeeAndAllocationYear(app.getEmployee(), (short) app.getFromDate().getYear())
                    .orElseThrow(() -> new IllegalArgumentException("Leave allocation not found"));

            // ✅ Update utilized balance
            alloc.setUtilizedBalance(alloc.getUtilizedBalance().add(totalDays));

            // ✅ Recalculate closing balance
            alloc.setClosingBalance(
                    alloc.getOpeningBalance()
                            .add(alloc.getAnnualAccrual())
                            .subtract(alloc.getUtilizedBalance())
                            .add(alloc.getAdjustments())
            );

            // ✅ Set appliedDate to current leave application's appliedDate
            alloc.setAppliedDate(app.getAppliedDate());

            // ✅ Set leaveDue to total valid days of this approved leave
            alloc.setLeaveDue(totalDays);  // <-- leaveDue updated here

            alloc.setModifiedDate(OffsetDateTime.now());
            leaveAllocationRepository.save(alloc);

            app.setAllocation(alloc);
            app.setStatus("Approved");
        } else {
            app.setStatus("Rejected");
        }

        app.setModifiedDate(OffsetDateTime.now());
        leaveApplicationRepository.save(app);

        return ResponseEntity.ok(new SuccessResponse<>("done", "Leave application processed successfully"));
    }

    @Override
    public List<LeaveTypeSummaryDTO> getAllLeaveTypeSummaries() {
        return leaveTypeRepository.findAll().stream()
                .map(leaveType -> new LeaveTypeSummaryDTO(leaveType.getId(), leaveType.getLeaveName()))
                .toList();
    }
}
