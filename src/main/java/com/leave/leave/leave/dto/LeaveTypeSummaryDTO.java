package com.leave.leave.leave.dto;

import java.util.UUID;

public class LeaveTypeSummaryDTO {
    private UUID id;
    private String name;

    public LeaveTypeSummaryDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
