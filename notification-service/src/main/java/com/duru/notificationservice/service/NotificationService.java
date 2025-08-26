package com.duru.notificationservice.service;

import com.duru.notificationservice.dto.NotificationResponse;
import com.duru.notificationservice.dto.TaskCreatedEvent;
import com.duru.notificationservice.model.Notification;
import com.duru.notificationservice.model.enums.NotificationType;
import com.duru.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public Page<NotificationResponse> getUserNotifications(Long userId, Pageable pageable) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId, pageable)
                .map(this::toDto);
    }

    @Transactional
    public void markAsRead(Long id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        repository.save(notification);
    }

    @Transactional
    public void createNotification(TaskCreatedEvent event) {
        Notification n = new Notification();
        n.setUserId(Long.parseLong(event.assignedTo()));
        n.setType(NotificationType.TASK_ASSIGNED);
        n.setMessage("Task" + event.taskId() + " -> " + event.title() + " : " + event.description());
        n.setReferenceId(Long.parseLong(event.taskId()));
        repository.save(n);
    }

    @Transactional
    public void handleTaskUpdated(TaskCreatedEvent event) {
        Notification n = new Notification();
        n.setUserId(Long.parseLong(event.assignedTo()));
        n.setType(NotificationType.TASK_UPDATED);
        n.setMessage("Task" + event.taskId() + " -> " + event.title() + " : " + event.description());
        n.setReferenceId(Long.parseLong(event.taskId()));
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
