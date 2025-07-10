package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "job_positions", schema = "erp")
public class JobPosition {

    @Id
    @Column(name = "position_id")
    private UUID positionId;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "position_code")
    private String positionCode;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private JobGrade grade;

    @Column(name = "skill_requirements")
    private String skillRequirements;

    @ManyToOne
    @JoinColumn(name = "reporting_manager_position")
    private JobPosition reportingManagerPosition;

    @Column(name = "succession_planning")
    private String successionPlanning;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    // Getters and Setters
}

