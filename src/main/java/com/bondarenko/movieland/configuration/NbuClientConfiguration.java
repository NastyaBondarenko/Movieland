package com.bondarenko.movieland.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class NbuClientConfiguration {
    @Value("${nbu.url}")
    private String url;

    @Bean
    public WebClient nbuWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}