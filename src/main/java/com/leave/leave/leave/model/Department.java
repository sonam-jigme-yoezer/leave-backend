package com.leave.leave.leave.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "departments", schema = "erp")
public class Department {

    @Id
    @Column(name = "dept_id")
    private UUID deptId;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_code")
    private String deptCode;

    @ManyToOne
    @JoinColumn(name = "parent_dept_id")
    private Department parentDepartment;

    @Column(name = "dept_head_id")
    private UUID deptHeadId;

    @Column(name = "budget_allocation")
    private BigDecimal budgetAllocation;

    @Column(name = "approval_hierarchy")
    private String approvalHierarchy; // Can map as String or JSON if needed

    @Column(name = "reporting_structure")
    private String reportingStructure; // Can map as String or JSON if needed

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    // Getters and Setters
}

