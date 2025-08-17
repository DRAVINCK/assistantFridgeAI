package com.example.assistantFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${GEMINI_API_BASE_URL}")
    private String chatGptUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder.baseUrl(chatGptUrl).build();
    }
}
