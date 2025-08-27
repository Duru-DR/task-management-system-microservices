package com.duru.notificationservice.controller;

import com.duru.notificationservice.dto.NotificationResponse;
import com.duru.notificationservice.model.Notification;
import com.duru.notificationservice.model.enums.NotificationType;
import com.duru.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification Management", description = "Endpoints for managing user Notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(
            summary = "List all user's notification",
            description = "Returns a paginated list of all user's notification.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of Notifications retrieved successfully")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Page<NotificationResponse>> getUserNotifications(
            @PathVariable Long id,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(notificationService.getUserNotifications(id, pageable));
    }

    @Operation(
            summary = "Mark Notification As Read",
            description = "Mark the notification as read.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Notification marked successfully")
            }
    )
    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }
}
