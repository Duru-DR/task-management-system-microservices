package com.duru.profileservice.controller;

import com.duru.profileservice.controller.ProfileController;
import com.duru.profileservice.dto.ProfileRequest;
import com.duru.profileservice.dto.ProfileResponse;
import com.duru.profileservice.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService service;

    @Test
    void createProfile_ShouldReturn201() throws Exception {
        ProfileRequest request = ProfileRequest.builder().username("Duru").build();
        ProfileResponse response = ProfileResponse.builder().id(1L).username("Duru").build();

        when(service.createProfile(any(ProfileRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"Duru\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("Duru"));
    }
}
