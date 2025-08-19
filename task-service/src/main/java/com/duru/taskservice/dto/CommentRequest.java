package com.duru.taskservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {
    @NotNull(message = "CreatedBy is required")
    private Long createdBy;

    @NotBlank(message = "Content cannot be empty")
    private String content;
}
