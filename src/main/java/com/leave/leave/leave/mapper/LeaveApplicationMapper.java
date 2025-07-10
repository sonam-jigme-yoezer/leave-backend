package com.leave.leave.leave.mapper;

import com.leave.leave.leave.dto.LeaveApplicationRequest;
import com.leave.leave.leave.model.Employee;
import com.leave.leave.leave.model.LeaveApplication;
import com.leave.leave.leave.model.LeaveType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class LeaveApplicationMapper {

    public LeaveApplication toEntity(LeaveApplicationRequest dto, Employee employee, LeaveType leaveType) {
        if (dto.getFromDate() == null || dto.getToDate() == null) {
            throw new IllegalArgumentException("Both fromDate and toDate must be provided.");
        }

        long daysBetween = ChronoUnit.DAYS.between(dto.getFromDate(), dto.getToDate()) + 1;
        if (daysBetween <= 0) {
            throw new IllegalArgumentException("toDate must be the same or after fromDate.");
        }

        LeaveApplication application = new LeaveApplication();
        application.setEmployee(employee);
        application.setLeaveType(leaveType);
        application.setFromDate(dto.getFromDate());
        application.setToDate(dto.getToDate());
        application.setTotalDays(java.math.BigDecimal.valueOf(daysBetween));
        application.setReason(dto.getReason());
        application.setMedicalCertificateAttached(dto.isMedicalCertificateAttached());
        application.setHandoverDetails(dto.getHandoverDetails());
        application.setStatus("Pending");
        application.setAppliedDate(LocalDate.now());
        application.setCreatedDate(OffsetDateTime.now());
        application.setModifiedDate(OffsetDateTime.now());

        return application;
    }

}

