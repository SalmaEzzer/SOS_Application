package com.sos.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI sosOpenApi() {
        return new OpenAPI().info(new Info()
                .title("SOS API")
                .version("v1")
                .description("API REST SOS. Les fonctionnalités métier ne sont pas encore disponibles."));
    }
}
