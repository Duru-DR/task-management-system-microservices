package com.duru.taskservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Service API")
                        .description("API for managing tasks and task comments within projects")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Fatima Ezzahra")
                                .email("fatimaeraqioui2022@gmail.com")
                                .url("https://github.com/Duru-DR"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Full Project Documentation")
                        .url("https://github.com/Duru-DR/task-management-system-microservices/blob/main/README.md"));
    }
}
