package com.duru.projectservice.service;

import com.duru.projectservice.dto.*;
import com.duru.projectservice.model.Project;
import com.duru.projectservice.model.ProjectMember;
import com.duru.projectservice.model.enums.ProjectRole;
import com.duru.projectservice.model.enums.ProjectStatus;
import com.duru.projectservice.repository.ProjectMemberRepository;
import com.duru.projectservice.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectResponse createProject(ProjectCreateRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setOwnerId(request.getOwnerId());
        project.setStatus(ProjectStatus.PLANNED);
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());

        ProjectMember owner = new ProjectMember();
        owner.setUserId(request.getOwnerId());
        owner.setRole(ProjectRole.OWNER);

        project.addProjectMember(owner);

        Project saved = projectRepository.save(project);

        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public ProjectResponse getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return mapToResponse(project);
    }

    @Transactional(readOnly = true)
    public Page<ProjectResponse> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public ProjectResponse addMember(Long projectId, Long userId, AddMemberRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        boolean isOwner = project.getMembers().stream()
                .anyMatch(m -> m.getUserId().equals(userId) && m.getRole() == ProjectRole.OWNER);

        if (!isOwner) {
            throw new SecurityException("Only project owner can add members");
        }

        boolean alreadyMember = project.getMembers().stream()
                .anyMatch(m -> m.getUserId().equals(request.getUserId()));
        if (alreadyMember) {
            throw new IllegalArgumentException("User is already a member of this project");
        }

        ProjectMember member = new ProjectMember();
        member.setUserId(request.getUserId());
        member.setRole(request.getRole());

        project.addProjectMember(member);
        projectRepository.save(project);

        return mapToResponse(project);
    }

    private ProjectResponse mapToResponse(Project project) {
        Set<ProjectMemberResponse> members = project.getMembers().stream()
                .map(m -> ProjectMemberResponse.builder()
                        .userId(m.getUserId())
                        .role(m.getRole())
                        .build())
                .collect(Collectors.toSet());

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .ownerId(project.getOwnerId())
                .status(project.getStatus())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .members(members)
                .build();
    }
}
