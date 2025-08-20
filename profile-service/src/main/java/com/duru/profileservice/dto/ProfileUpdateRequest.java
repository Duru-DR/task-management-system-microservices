package com.duru.profileservice.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateRequest {

    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;

    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

    @Size(max = 255, message = "Avatar URL must not exceed 255 characters")
    private String avatarUrl;

    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String location;
}
