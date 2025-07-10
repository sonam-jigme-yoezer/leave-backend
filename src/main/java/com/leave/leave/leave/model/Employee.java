package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "employees", schema = "erp")
public class Employee {

    @Id
    @Column(name = "emp_id")
    private UUID empId;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private JobPosition position;

    @Column(name = "emp_code")
    private String empCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @Column(name = "cid_number")
    private String cidNumber;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "employment_status")
    private String employmentStatus;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "structure_id")
    private SalaryStructure structure;



    public Employee() {
    }

}

