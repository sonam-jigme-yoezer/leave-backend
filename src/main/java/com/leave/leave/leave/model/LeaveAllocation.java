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
@Table(name = "leave_allocations", schema = "erp")
public class LeaveAllocation {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "allocation_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @Column(name = "allocation_year")
    private Short allocationYear;

    @Column(name = "opening_balance")
    private BigDecimal openingBalance;

    @Column(name = "annual_accrual")
    private BigDecimal annualAccrual;

    @Column(name = "adjustments")
    private BigDecimal adjustments;

    @Column(name = "utilized_balance")
    private BigDecimal utilizedBalance;

    @Column(name = "expired_balance")
    private BigDecimal expiredBalance;

    @Column(name = "closing_balance")
    private BigDecimal closingBalance;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name = "no_of_working_days")
    private BigDecimal noOfWorkingDays;

    @Column(name = "leave_due")
    private BigDecimal leaveDue;

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    // Getters & Setters...
}

