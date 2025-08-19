package com.duru.projectservice.dto;

import com.duru.projectservice.model.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMemberRequest {

    @NotNull(message = "UserId is required")
    private Long userId;

    @NotNull(message = "Role is required")
    private ProjectRole role;
}
