package com.duru.profileservice.controller;

import com.duru.profileservice.dto.ProfileRequest;
import com.duru.profileservice.dto.ProfileResponse;
import com.duru.profileservice.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(service.createProfile(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProfileById(id));
    }

    @GetMapping("/me/{userId}")
    public ResponseEntity<ProfileResponse> getMyProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getMyProfile(userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileRequest request) {
        return ResponseEntity.ok(service.updateProfile(id, request));
    }
}
