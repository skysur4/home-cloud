package io.cloud.home.config;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
public class WebConfig {
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}