package com.bondarenko.movieland.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class TaskExecutorConfiguration {

    @Bean
    public Executor getTaskExecutor(@Value("${executor.max.pool.size}") int maxPoolSize,
                                    @Value("${executor.core.pool.size}") int corePoolSize,
                                    @Value("${executor.queue.capacity}") int queueCapacity,
                                    @Value("${executor.thread.name.prefix}") String threadNamePrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }
}