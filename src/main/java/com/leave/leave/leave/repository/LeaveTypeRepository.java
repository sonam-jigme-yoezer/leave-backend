package com.leave.leave.leave.repository;

import com.leave.leave.leave.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, UUID> {
    Optional<LeaveType> findByLeaveName(String leaveName);
}
