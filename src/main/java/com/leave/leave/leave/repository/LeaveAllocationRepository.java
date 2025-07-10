package com.leave.leave.leave.repository;

import com.leave.leave.leave.model.Employee;
import com.leave.leave.leave.model.LeaveAllocation;
import com.leave.leave.leave.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LeaveAllocationRepository extends JpaRepository<LeaveAllocation, UUID> {
    Optional<LeaveAllocation> findByEmployeeEmpIdAndAllocationYear(UUID empId, Short allocationYear);

    Optional<LeaveAllocation> findByEmployeeAndAllocationYear(Employee employee, Short allocationYear);



}
