package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "salary_structures", schema = "erp")
public class SalaryStructure {

    @Id
    @Column(name = "structure_id")
    private UUID structureId;

    @Column(name = "org_id")
    private UUID orgId;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private JobGrade grade;

    @Column(name = "structure_name")
    private String structureName;

    @Column(name = "component_id")
    private UUID componentId;

    @Column(name = "component_value")
    private BigDecimal componentValue;

    @Column(name = "is_variable")
    private Boolean isVariable;

    @Column(name = "performance_linked")
    private Boolean performanceLinked;

    @Column(name = "revision_cycle")
    private String revisionCycle;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

}
