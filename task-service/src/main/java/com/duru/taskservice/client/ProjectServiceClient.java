package com.duru.taskservice.client;

import com.duru.taskservice.client.enums.ProjectRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "project-service")
public interface ProjectServiceClient {

    @GetMapping("/api/v1/projects/{projectId}/members/{userId}/role")
    ProjectRole getUserRole(
            @PathVariable("projectId") Long projectId,
            @PathVariable("userId") Long userId);
}

