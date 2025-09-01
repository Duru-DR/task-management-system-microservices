package com.duru.profileservice.service;

import com.duru.profileservice.dto.ProfileRequest;
import com.duru.profileservice.dto.ProfileResponse;
import com.duru.profileservice.model.Profile;
import com.duru.profileservice.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository repository;

    @InjectMocks
    private ProfileService service;

    @Test
    void createProfile_ShouldReturnProfileResponse() {
        ProfileRequest request = ProfileRequest.builder()
                .username("Duru")
                .fullName("Fatima Ezzahra")
                .build();

        Profile profile = Profile.builder()
                .id(1L)
                .username("Duru")
                .fullName("Fatima Ezzahra")
                .build();

        when(repository.save(any(Profile.class))).thenReturn(profile);

        ProfileResponse response = service.createProfile(request);

        assertEquals("Duru", response.getUsername());
        assertEquals(1L, response.getId());
    }

    @Test
    void getProfileById_ShouldThrow_WhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getProfileById(99L));
    }
}
