package com.bondarenko.movieland.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("currency");
    }

    @CacheEvict(value = {"currency"}, allEntries = true)
    @Scheduled(cron = "${scheduled.caching.currency}", timeUnit = TimeUnit.HOURS)
    public void clearCache() {
        log.info("Cache 'currency' cleared");
    }
}