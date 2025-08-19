package com.duru.notificationservice.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationType {
    TASK_ASSIGNED("Task Assigned"),
    TASK_UPDATED("Task Updated"),
    PROJECT_INTEGRATION("Project Integration"),
    PROJECT_UPDATED("Project Updated"),
    COMMENT_ADDED("Comment Added"),
    DEADLINE_REMINDER("Deadline Reminder");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
