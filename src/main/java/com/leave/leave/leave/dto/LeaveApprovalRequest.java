package com.leave.leave.leave.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LeaveApprovalRequest {
    private UUID applicationId;
    private String action; // "APPROVE" or "REJECT"
    private String rejectionReason; // nullable, only for rejecte

    // getters/setters
}

