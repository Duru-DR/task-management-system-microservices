package com.duru.taskservice.client.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectRole {
    ADMIN("Admin"),
    OWNER("Owner"),
    MEMBER("Member");

    private final String role;

    ProjectRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }
}