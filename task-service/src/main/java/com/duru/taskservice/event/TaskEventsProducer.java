package com.duru.taskservice.event;

import static com.duru.taskservice.config.TaskEventsTopicConfig.TASK_EVENTS_TOPIC;

import java.util.UUID;

import com.duru.taskservice.dto.TaskCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskEventsProducer {

    private final KafkaTemplate<String, TaskCreatedEvent> kafkaTemplate;

    public void publishTaskCreated(String taskId, String title, String description, String assignedTo, String eventType) {
        var event = new TaskCreatedEvent(
                UUID.randomUUID().toString(),
                taskId,
                title,
                description,
                assignedTo,
                eventType,
                System.currentTimeMillis()
        );
        kafkaTemplate.send(TASK_EVENTS_TOPIC, taskId, event);
    }
}
