package com.duru.profileservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private Long id;
    private String username;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private String location;
}