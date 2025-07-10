package com.leave.leave.leave.repository;

import com.leave.leave.leave.dto.LeaveApplicationResponseDTO;
import com.leave.leave.leave.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, UUID> {

    // Today's leave
    @Query("SELECT new com.leave.leave.leave.dto.LeaveApplicationResponseDTO(" +
            "e.empId, " +              // select UUID here
            "e.empCode, " +
            "TRIM(CONCAT(e.firstName, ' ', COALESCE(e.middleName, ''), ' ', e.lastName)), " +
            "d.deptName, la.fromDate, la.toDate, la.totalDays, la.reason) " +
            "FROM LeaveApplication la " +
            "JOIN la.employee e " +
            "JOIN e.department d " +
            "WHERE la.appliedDate = :date")
    List<LeaveApplicationResponseDTO> findApplicationsByAppliedDate(LocalDate date);

    // Last 5 days
    @Query("SELECT new com.leave.leave.leave.dto.LeaveApplicationResponseDTO(" +
            "e.empId, " +
            "e.empCode, " +
            "TRIM(CONCAT(e.firstName, ' ', COALESCE(e.middleName, ''), ' ', e.lastName)), " +
            "d.deptName, la.fromDate, la.toDate, la.totalDays, la.reason) " +
            "FROM LeaveApplication la " +
            "JOIN la.employee e " +
            "JOIN e.department d " +
            "WHERE la.fromDate >= :startDate AND la.fromDate <= :endDate")
    List<LeaveApplicationResponseDTO> findRecentLeaveApplications(LocalDate startDate, LocalDate endDate);

    @Query("SELECT MAX(la.appliedDate) FROM LeaveApplication la WHERE la.employee.empId = :empId")
    Optional<LocalDate> findLatestAppliedDateByEmployee(UUID empId);



}
