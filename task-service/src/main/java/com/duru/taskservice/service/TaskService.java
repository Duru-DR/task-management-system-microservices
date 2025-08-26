package com.duru.taskservice.service;

import com.duru.taskservice.client.ProjectServiceClient;
import com.duru.taskservice.dto.*;
import com.duru.taskservice.client.enums.ProjectRole;
import com.duru.taskservice.exception.ResourceNotFoundException;
import com.duru.taskservice.model.Task;
import com.duru.taskservice.model.TaskComment;
import com.duru.taskservice.repository.TaskCommentRepository;
import com.duru.taskservice.repository.TaskRepository;
import com.duru.taskservice.event.TaskEventsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskCommentRepository commentRepository;
    private final TaskEventsProducer taskEventsProducer;
    private final ProjectServiceClient projectServiceClient;

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
//        ProjectRole role = projectServiceClient.getUserRole(
//                request.getProjectId(),
//                request.getCreatedBy()
//        );
//
//        if (role != ProjectRole.OWNER && role != ProjectRole.ADMIN) {
//            throw new SecurityException("Only project owner or admin can assign tasks");
//        }

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProjectId(request.getProjectId());
        task.setCreatedBy(request.getCreatedBy());
        task.setAssignedTo(request.getAssignedTo());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());

        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }

        Task saved = taskRepository.save(task);
        taskEventsProducer.publishTaskCreated(
                String.valueOf(task.getId()),
                task.getTitle(),
                task.getDescription(),
                String.valueOf(task.getAssignedTo()),
                "Task Assigned"
        );
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return mapToResponse(task);
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        if (request.getTitle() != null) task.setTitle(request.getTitle());
        if (request.getDescription() != null) task.setDescription(request.getDescription());
        if (request.getAssignedTo() != null) task.setAssignedTo(request.getAssignedTo());
        if (request.getPriority() != null) task.setPriority(request.getPriority());
        if (request.getStatus() != null) task.setStatus(request.getStatus());
        if (request.getDueDate() != null) task.setDueDate(request.getDueDate());

        Task updated = taskRepository.save(task);
        taskEventsProducer.publishTaskCreated(
                String.valueOf(task.getId()),
                task.getTitle(),
                task.getDescription(),
                String.valueOf(task.getAssignedTo()),
                "Task Updated"
        );
        return mapToResponse(updated);
    }

    @Transactional
    public void addComment(Long taskId, CommentRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + taskId + " not found"));

        TaskComment comment = new TaskComment();
        comment.setTask(task);
        comment.setCreatedBy(request.getCreatedBy());
        comment.setContent(request.getContent());

        commentRepository.save(comment);
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .projectId(task.getProjectId())
                .createdBy(task.getCreatedBy())
                .assignedTo(task.getAssignedTo())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
