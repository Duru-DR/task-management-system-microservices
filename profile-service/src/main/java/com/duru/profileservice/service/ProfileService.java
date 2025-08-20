package com.duru.profileservice.service;

import com.duru.profileservice.dto.*;
import com.duru.profileservice.model.Profile;
import com.duru.profileservice.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileResponse createProfile(ProfileRequest request) {
        Profile profile = Profile.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .bio(request.getBio())
                .avatarUrl(request.getAvatarUrl())
                .location(request.getLocation())
                .build();

        Profile saved = repository.save(profile);
        return mapToResponse(saved);
    }

    public ProfileResponse getProfileById(Long id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
        return mapToResponse(profile);
    }

    public ProfileResponse getMyProfile(Long userId) {
        // for the moment it s the same as getProfileById
        Profile profile = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + userId));
        return mapToResponse(profile);
    }

    public ProfileResponse updateProfile(Long id, ProfileUpdateRequest request) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));

        if (request.getFullName() != null) profile.setFullName(request.getFullName());
        if (request.getBio() != null) profile.setBio(request.getBio());
        if (request.getAvatarUrl() != null) profile.setAvatarUrl(request.getAvatarUrl());
        if (request.getLocation() != null) profile.setLocation(request.getLocation());

        Profile updated = repository.save(profile);
        return mapToResponse(updated);
    }

    private ProfileResponse mapToResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .username(profile.getUsername())
                .fullName(profile.getFullName())
                .bio(profile.getBio())
                .avatarUrl(profile.getAvatarUrl())
                .location(profile.getLocation())
                .build();
    }
}
