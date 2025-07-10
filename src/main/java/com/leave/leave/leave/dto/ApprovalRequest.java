package com.leave.leave.leave.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalRequest {
    private boolean approved;    // true = approve, false = reject
    private String reason;       // required if rejected
}