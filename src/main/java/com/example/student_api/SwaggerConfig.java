package com.example.student_api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student API")
                        .description("A Spring Boot REST API for managing students")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Student API Team")
                                .email("contact@example.com")));
    }
} 