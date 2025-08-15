package com.duru.projectservice.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectStatus {
    PLANNED("Planned"),
    IN_PROGRESS("In_progress"),
    COMPLETED("Completed");

    private final String status;

    ProjectStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
