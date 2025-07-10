package com.leave.leave.leave.repository;

import com.leave.leave.leave.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByCidNumber(String cidNumber);

}
