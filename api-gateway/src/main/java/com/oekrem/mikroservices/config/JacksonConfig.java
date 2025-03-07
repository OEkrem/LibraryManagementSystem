package com.oekrem.mikroservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return Jackson2ObjectMapperBuilder.json()
                .failOnUnknownProperties(false);  // Unknown properties yoksayılır
    }

}
