package com.leave.leave.leave.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "leave_calendars", schema = "erp")
public class LeaveCalendar {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "calendar_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization org;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(name = "calendar_year")
    private Short year;

    @Column(name = "holiday_date")
    private LocalDate holidayDate;

    @Column(name = "holiday_name")
    private String holidayName;

    @Column(name = "holiday_type")
    private String holidayType;

    @Column(name = "is_optional")
    private Boolean isOptional;

    @Column(name = "weekend_definition", columnDefinition = "jsonb")
    private String weekendDefinition;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;



    // Getters & Setters...
}
