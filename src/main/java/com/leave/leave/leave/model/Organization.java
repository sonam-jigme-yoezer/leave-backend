package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "organizations", schema = "erp")
public class Organization {

    @Id
    @Column(name = "org_id")
    private UUID orgId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_code")
    private String orgCode;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "dzongkhag")
    private String dzongkhag;

    @Column(name = "thromde")
    private String thromde;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "tax_jurisdiction")
    private String taxJurisdiction;

    @Column(name = "legal_entity_structure")
    private String legalEntityStructure;

    @Column(name = "tax_registration_numbers")
    private String taxRegistrationNumbers;

    @ManyToOne
    @JoinColumn(name = "parent_org_id")
    private Organization parentOrganization;

    @Column(name = "org_level")
    private Short orgLevel;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    // Getters and Setters
}
