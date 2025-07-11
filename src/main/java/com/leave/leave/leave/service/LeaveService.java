package com.leave.leave.leave.service;

import com.leave.leave.leave.dto.LeaveApplicationRequest;
import com.leave.leave.leave.dto.LeaveApprovalRequest;
import com.leave.leave.leave.dto.LeaveTypeSummaryDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LeaveService {
    ResponseEntity<?> submitLeaveApplication(LeaveApplicationRequest request);

    ResponseEntity<?> getRecentLeaves(String mode, LocalDate current);

    ResponseEntity<?> getApprovalsByEmployee(UUID empId);

    ResponseEntity<?> approveLeave(UUID id, boolean approved, String reason);

    List<LeaveTypeSummaryDTO> getAllLeaveTypeSummaries();
}
