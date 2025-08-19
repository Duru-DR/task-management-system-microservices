package com.duru.projectservice.dto;

import com.duru.projectservice.model.enums.ProjectStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private ProjectStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<ProjectMemberResponse> members;
}
