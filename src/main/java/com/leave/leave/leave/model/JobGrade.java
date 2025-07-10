package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "job_grades", schema = "erp")
public class JobGrade {

    @Id
    @Column(name = "grade_id")
    private UUID gradeId;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @Column(name = "grade_name")
    private String gradeName;

    @Column(name = "grade_code")
    private String gradeCode;

    @Column(name = "min_salary")
    private BigDecimal minSalary;

    @Column(name = "max_salary")
    private BigDecimal maxSalary;

    @Column(name = "progression_rules")
    private String progressionRules; // Can map as String or JSON (advanced)

    @Column(name = "benefit_entitlements")
    private String benefitEntitlements; // Can map as String or JSON (advanced)

    @Column(name = "performance_criteria")
    private String performanceCriteria; // Can map as String or JSON (advanced)

    @ManyToOne
    @JoinColumn(name = "next_grade_id")
    private JobGrade nextGrade;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    // Getters and Setters
}
