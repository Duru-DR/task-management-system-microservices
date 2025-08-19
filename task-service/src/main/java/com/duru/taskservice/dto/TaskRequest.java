package com.duru.taskservice.dto;

import com.duru.taskservice.model.enums.TaskPriority;
import com.duru.taskservice.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "ProjectId is required")
    private Long projectId;

    @NotNull(message = "CreatedBy is required")
    private Long createdBy;

    private Long assignedTo;

    private TaskPriority priority;

    private TaskStatus status;

    private LocalDateTime dueDate;
}
