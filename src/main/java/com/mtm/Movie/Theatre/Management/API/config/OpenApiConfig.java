package com.mtm.Movie.Theatre.Management.API.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie Theatre Management API")
                        .version("v1.0")
                        .description("API for managing movies, bookings, users, and authentication")
                        .contact(new Contact()
                                .name("Lauretta Backend")
                                .email("lauretta@example.com")
                                .url("https://www.linkedin.com/in/lauretta-okolie-6b1a3422a/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/Laurettta"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Dev Server")
                ));
    }
}
