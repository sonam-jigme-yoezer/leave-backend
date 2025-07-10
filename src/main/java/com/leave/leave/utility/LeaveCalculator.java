package com.leave.leave.utility;

import com.leave.leave.leave.model.Employee;
import com.leave.leave.leave.model.LeaveCalendar;
import com.leave.leave.leave.repository.LeaveCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LeaveCalculator {

    @Autowired
    private LeaveCalendarRepository leaveCalendarRepository;

    public int countLeaveDaysExcludingWeekendsAndHolidays(Employee employee, LocalDate from, LocalDate to) {
        // Get the employee's organization ID
        UUID orgId = employee.getOrganization().getOrgId();

        // Fetch holidays for this org within the date range
        List<LeaveCalendar> holidays = leaveCalendarRepository.findByOrg_OrgIdAndHolidayDateBetween(orgId, from, to);

        Set<LocalDate> holidayDates = holidays.stream()
                .map(LeaveCalendar::getHolidayDate)
                .collect(Collectors.toSet());

        int count = 0;
        for (LocalDate date = from; !date.isAfter(to); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) continue;       // Skip Sundays
            if (holidayDates.contains(date)) continue;                   // Skip holidays
            count++;
        }

        return count;
    }

}

