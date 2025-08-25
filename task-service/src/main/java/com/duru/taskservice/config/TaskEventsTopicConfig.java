package com.duru.taskservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskEventsTopicConfig {

    public static final String TASK_EVENTS_TOPIC = "task-events";

    @Bean
    public NewTopic taskEventsTopic() {
        return new NewTopic(TASK_EVENTS_TOPIC, 1, (short) 1);
    }
}