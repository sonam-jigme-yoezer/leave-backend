package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "branches", schema = "erp")
public class Branch {

    @Id
    @Column(name = "branch_id")
    private UUID branchId;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "thromde")
    private String thromde;

    @Column(name = "dzongkhag")
    private String dzongkhag;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "regional_manager_id")
    private UUID regionalManagerId;

    @Column(name = "operational_status")
    private Boolean operationalStatus;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    // Getters and Setters
}

