package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "leave_approvals", schema = "erp")
public class LeaveApproval {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "approval_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private LeaveApplication application;

    @ManyToOne
    @JoinColumn(name = "approver_emp_id")
    private Employee approver;

    @Column(name = "approval_level")
    private Short approvalLevel;

    @Column(name = "approval_status")
    private String status;

    @Column(name = "approval_date")
    private OffsetDateTime approvalDate;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "delegation_reference")
    private UUID delegationReference;

    @Column(name = "escalation_flag")
    private Boolean escalationFlag;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;

    // Getters & Setters...
}

