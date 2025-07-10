package com.leave.leave.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class LeaveApplicationResponseDTO {
    private UUID empId;
    private String empCode;
    private String name;       // Concatenated full name
    private String division;   // This receives deptName
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal totalDays;
    private String reason;

    public LeaveApplicationResponseDTO(UUID empId, String empCode, String name,
                                       String division, LocalDate fromDate, LocalDate toDate,
                                       BigDecimal totalDays, String reason) {
        this.empId = empId;
        this.empCode = empCode;
        this.name = name;
        this.division = division;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalDays = totalDays;
        this.reason = reason;
    }

}

