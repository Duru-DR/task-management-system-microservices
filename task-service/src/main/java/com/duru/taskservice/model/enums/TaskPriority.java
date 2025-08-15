package com.duru.taskservice.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

    @JsonValue
    public String getPriority() {
        return priority;
    }
}
