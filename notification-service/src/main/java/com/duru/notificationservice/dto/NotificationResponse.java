package com.duru.notificationservice.dto;

import com.duru.notificationservice.model.enums.NotificationType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {
    private Long id;
    private NotificationType type;
    private String message;
    private Boolean read;
    private Long referenceId;
    private LocalDateTime createdAt;
}
