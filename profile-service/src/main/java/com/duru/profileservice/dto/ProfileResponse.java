package com.duru.profileservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    @Schema(description = "User Identifier", example = "1")
    private Long id;

    @Schema(description = "Unique username of the profile", example = "Duru")
    private String username;

    @Schema(description = "Full name of the user", example = "Fatima Ezzahra")
    private String fullName;

    @Schema(description = "Short biography", example = "Backend developer, Spring Boot enthusiast")
    private String bio;

    @Schema(description = "Link to avatar image", example = "https://example.com/avatar.png")
    private String avatarUrl;

    @Schema(description = "User location", example = "Casablanca, Morocco")
    private String location;
}