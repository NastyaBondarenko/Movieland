package com.bondarenko.movieland.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.properties"})
@ComponentScan(basePackages = {"com.bondarenko.movieland"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.bondarenko\\.movieland\\.web..*")})
public class AppConfiguration {

}