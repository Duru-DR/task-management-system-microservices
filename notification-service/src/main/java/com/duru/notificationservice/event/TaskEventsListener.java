package com.duru.notificationservice.event;

import com.duru.notificationservice.dto.TaskCreatedEvent;
import com.duru.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskEventsListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "task-events", groupId = "notification-service")
    public void onTaskCreated(
            TaskCreatedEvent event,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            ConsumerRecord<String, TaskCreatedEvent> record) {
        System.out.println(event);
        System.out.println(event.title() + " " + event.description());
        System.out.println(record.value());
        System.out.println("++++++++++++++++++++++++++++++++++++++\n++++++++++++++++++++++++++++++++++++++\n+++++++NOTIF EVENT TO CAPTURE ++++++++++++++++++++\n++++++++++++++++++++++++++++++++++\n++++++++++++++++++++++++++++");
        notificationService.handleTaskCreated(event);
    }
}
