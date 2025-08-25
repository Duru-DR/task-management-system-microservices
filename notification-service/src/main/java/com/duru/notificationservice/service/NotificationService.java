package com.duru.notificationservice.service;

import com.duru.notificationservice.dto.NotificationResponse;
import com.duru.notificationservice.dto.TaskCreatedEvent;
import com.duru.notificationservice.model.Notification;
import com.duru.notificationservice.model.enums.NotificationType;
import com.duru.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public List<NotificationResponse> getUserNotifications(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markAsRead(Long id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        repository.save(notification);
    }

    @Transactional
    public void createNotification(Long userId, NotificationType type, String message, Long referenceId) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setType(type);
        n.setMessage(message);
        n.setReferenceId(referenceId);
        repository.save(n);
    }

    @Transactional
    public void handleTaskCreated(TaskCreatedEvent event) {
        Notification n = new Notification();
        n.setUserId((long)1);
        n.setMessage("hello");
        repository.save(n);
    }

    private NotificationResponse toDto(Notification n) {
        NotificationResponse dto = new NotificationResponse();
        dto.setId(n.getId());
        dto.setType(n.getType());
        dto.setMessage(n.getMessage());
        dto.setRead(n.getRead());
        dto.setReferenceId(n.getReferenceId());
        dto.setCreatedAt(n.getCreatedAt());
        return dto;
    }
}
