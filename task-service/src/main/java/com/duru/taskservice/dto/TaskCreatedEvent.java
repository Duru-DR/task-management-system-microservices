package com.duru.taskservice.dto;

public record TaskCreatedEvent(
        String eventId,
        String taskId,
        String title,
        String description,
        String assignedTo,
        String eventType,
        long occurredAtEpochMs
) {}