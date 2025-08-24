package com.duru.taskservice.controller;

import com.duru.taskservice.dto.*;
import com.duru.taskservice.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Endpoints for managing tasks and comments")
public class TaskController {

    private final TaskService taskService;

    @Operation(
            summary = "Create a new task",
            description = "Creates a task for a specific project. " +
                    "Only project owners/admins should be allowed to assign tasks (TODO: permission check).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation failed")
            }
    )
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @Operation(
            summary = "Get task by ID",
            description = "Fetches a single task by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @Operation(
            summary = "Update task",
            description = "Updates fields of an existing task (partial update allowed)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @Operation(
            summary = "Add comment to task",
            description = "Adds a new comment to a given task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Comment added successfully"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<Void> addComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequest request) {
        taskService.addComment(id, request);
        return ResponseEntity.ok().build();
    }
}
