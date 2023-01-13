package com.bondarenko.movieland.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class CachedPoolConfiguration {
    @Bean
    public ExecutorService taskExecutor() {
        return Executors.newCachedThreadPool();
    }
}
