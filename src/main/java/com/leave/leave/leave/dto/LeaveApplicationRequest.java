package com.leave.leave.leave.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class LeaveApplicationRequest {
    private String cid;
    private String leaveName;  // changed from leaveTypeId to leaveName
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private boolean medicalCertificateAttached;
    private String handoverDetails;
}

