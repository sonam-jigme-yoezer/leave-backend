package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "leave_applications", schema = "erp")
public class LeaveApplication {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "application_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "total_days")
    private BigDecimal totalDays;

    @Column(name = "reason")
    private String reason;

    @Column(name = "medical_certificate_attached")
    private boolean medicalCertificateAttached;

    @Column(name = "handover_details")
    private String handoverDetails;

    @Column(name = "application_status")
    private String status;

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "allocation_id")
    private LeaveAllocation allocation;

    // Getters & Setters...
}
