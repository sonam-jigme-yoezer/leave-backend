package com.leave.leave.leave.controller;

import com.leave.leave.leave.dto.ApprovalRequest;
import com.leave.leave.leave.dto.LeaveApplicationRequest;
import com.leave.leave.leave.dto.LeaveApprovalRequest;
import com.leave.leave.leave.model.Employee;
import com.leave.leave.leave.model.LeaveType;
import com.leave.leave.leave.repository.EmployeeRepository;
import com.leave.leave.leave.repository.LeaveApplicationRepository;
import com.leave.leave.leave.repository.LeaveTypeRepository;
import com.leave.leave.leave.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @PostMapping("/requestLeave")
    public ResponseEntity<?> applyLeave(@RequestBody LeaveApplicationRequest request) {
        return leaveService.submitLeaveApplication(request);
    }

    @GetMapping("/applications/recent/{mode}")
    public ResponseEntity<?> getRecentLeaves(@PathVariable("mode") String mode) {
        return leaveService.getRecentLeaves(mode, LocalDate.now());
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<?> getApprovalsByEmployee(@PathVariable UUID empId) {
        return leaveService.getApprovalsByEmployee(empId);
    }

//    @PostMapping("/leave/approval")
//    public ResponseEntity<?> approveOrRejectLeave(@RequestBody LeaveApprovalRequestDTO request) {
//        return leaveService.approveOrRejectLeave(request);
//    }

    @PostMapping("/leave/{id}/approve")
    public ResponseEntity<?> approveLeave(
            @PathVariable UUID id,
            @RequestBody ApprovalRequest request
    ) {
        return leaveService.approveLeave(id, request.isApproved(), request.getReason());
    }




}
