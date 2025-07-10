package com.leave.leave.leave.repository;

import com.leave.leave.leave.model.Branch;
import com.leave.leave.leave.model.LeaveCalendar;
import com.leave.leave.leave.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LeaveCalendarRepository extends JpaRepository<LeaveCalendar, UUID> {

    List<LeaveCalendar> findByOrg_OrgIdAndHolidayDateBetween(UUID orgId, LocalDate from, LocalDate to);


}
