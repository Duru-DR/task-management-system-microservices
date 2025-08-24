package com.duru.profileservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest {

    @Schema(description = "Unique username of the profile", example = "Duru")
    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String username;

    @Schema(description = "Full name of the user", example = "Fatima Ezzahra")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;

    @Schema(description = "Short biography", example = "Backend developer, Spring Boot enthusiast")
    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

    @Schema(description = "Link to avatar image", example = "https://example.com/avatar.png")
    @Size(max = 255, message = "Avatar URL must not exceed 255 characters")
    private String avatarUrl;

    @Schema(description = "User location", example = "Casablanca, Morocco")
    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String location;
}
