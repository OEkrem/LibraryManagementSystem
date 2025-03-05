package com.oekrem.mikroservices.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Kütüphane API")
                        .version("1.0")
                        .description("Ödünç yönetimi için oluşturulmuş REST API dokümantasyonu."));
    }
}