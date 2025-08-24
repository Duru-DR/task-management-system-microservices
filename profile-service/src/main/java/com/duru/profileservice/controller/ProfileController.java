package com.duru.profileservice.controller;

import com.duru.profileservice.dto.*;
import com.duru.profileservice.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@Tag(name = "Profiles", description = "API for managing user profiles")
public class ProfileController {

    private final ProfileService service;

    @Operation(summary = "Create a new profile",
            description = "Creates a new profile with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profile created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfileResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProfile(request));
    }

    @Operation(summary = "Get profile by ID",
            description = "Retrieve a profile using its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(
            @Parameter(description = "Profile ID", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.getProfileById(id));
    }

    @Operation(summary = "Get my profile",
            description = "Retrieve the current user’s profile using their user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/me/{userId}")
    public ResponseEntity<ProfileResponse> getMyProfile(
            @Parameter(description = "Profile ID", example = "1") @PathVariable Long userId) {
        return ResponseEntity.ok(service.getMyProfile(userId));
    }

    @Operation(summary = "Update profile",
            description = "Partially update an existing profile with new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated successfully",
                    content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(
            @Parameter(description = "Profile ID", example = "1") @PathVariable Long id,
            @Valid @RequestBody ProfileUpdateRequest request) {
        return ResponseEntity.ok(service.updateProfile(id, request));
    }
}
