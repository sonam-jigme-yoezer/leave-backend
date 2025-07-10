package com.leave.leave.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaveApprovalResponseDTO {
    private String empCode;
    private String name;
    private String reason;
    private String leaveName;
    private LocalDate appliedDate;
    private String status;
}