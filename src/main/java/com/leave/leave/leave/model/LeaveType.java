package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "leave_types", schema = "erp")
public class LeaveType {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "leave_type_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization org;

    @Column(name = "leave_name")
    private String leaveName;

    @Column(name = "leave_code", unique = true)
    private String code;

    @Column(name = "accrual_rules", columnDefinition = "jsonb")
    private String accrualRules;

    @Column(name = "carry_forward_policy", columnDefinition = "jsonb")
    private String carryForwardPolicy;

    @Column(name = "encashment_rules", columnDefinition = "jsonb")
    private String encashmentRules;

    @Column(name = "max_days_per_application")
    private Short maxDays;

    @Column(name = "requires_medical_certificate")
    private boolean requiresMedicalCertificate;

    @Column(name = "gender_specific")
    private String genderSpecific;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;


    // Getters & Setters...
}

