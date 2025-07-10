package com.leave.leave.leave.repository;

import com.leave.leave.leave.dto.LeaveApplicationResponseDTO;
import com.leave.leave.leave.dto.LeaveApprovalResponseDTO;
import com.leave.leave.leave.model.LeaveApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeaveApprovalRepository extends JpaRepository<LeaveApproval, UUID> {

    @Query("SELECT new com.leave.leave.leave.dto.LeaveApprovalResponseDTO(" +
            "e.empCode, " +
            "TRIM(CONCAT(e.firstName, ' ', COALESCE(e.middleName, ''), ' ', e.lastName)), " +
            "la.reason, " +
            "lt.leaveName, " +
            "la.appliedDate, " +
            "laa.status) " +
            "FROM LeaveApproval laa " +
            "JOIN laa.application la " +
            "JOIN la.employee e " +
            "JOIN la.leaveType lt " +
            "WHERE e.empId = :empId")
    List<LeaveApprovalResponseDTO> findApprovalsByEmployee(@Param("empId") UUID empId);
}
