package com.duru.projectservice.controller;

import com.duru.projectservice.dto.*;
import com.duru.projectservice.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@Tag(name = "Project Management", description = "Endpoints for managing projects and project members")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(
            summary = "Create a new project",
            description = "Creates a new project with the given details. The owner is automatically added as a member.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Project created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request body"),
            }
    )
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectCreateRequest request) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(projectService.createProject(request));
    }

    @Operation(
            summary = "Get project by ID",
            description = "Retrieves a project with its members by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Project found"),
                    @ApiResponse(responseCode = "404", description = "Project not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @Operation(
            summary = "List all projects",
            description = "Returns a paginated list of all projects in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of projects retrieved successfully")
            }
    )
    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> getAllProjects(Pageable pageable) {
        return ResponseEntity.ok(projectService.getAllProjects(pageable));
    }

    @Operation(
            summary = "Add member to a project",
            description = "Allows the project owner to add a new member to the project.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Member added successfully"),
                    @ApiResponse(responseCode = "403", description = "Only the project owner can add members"),
                    @ApiResponse(responseCode = "404", description = "Project not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid request or user already a member")
            }
    )
    @PostMapping("/{id}/member/{userId}")
    public ResponseEntity<ProjectResponse> addMember(
            @PathVariable Long id,
            @PathVariable Long userId,
            @Valid @RequestBody AddMemberRequest request) {
        return ResponseEntity.ok(projectService.addMember(id, userId, request));
    }
}
