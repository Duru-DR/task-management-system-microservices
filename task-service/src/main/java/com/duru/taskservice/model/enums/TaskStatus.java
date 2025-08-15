package com.duru.taskservice.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;


public enum TaskStatus {
    TODO("ToDo"),
    IN_PROGRESS("In_progress"),
    DONE("Done");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}