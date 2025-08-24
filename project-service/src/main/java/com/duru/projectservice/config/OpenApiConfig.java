package com.duru.projectservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI projectServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Service API")
                        .description("API for creating and managing collaborative projects")
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
