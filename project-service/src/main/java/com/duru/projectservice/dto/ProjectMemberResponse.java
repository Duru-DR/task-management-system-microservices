package com.duru.projectservice.dto;

import com.duru.projectservice.model.enums.ProjectRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMemberResponse {
    private Long userId;
    private ProjectRole role;
}
