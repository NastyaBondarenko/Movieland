package com.bondarenko.movieland.cache;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Component
@Retention(RetentionPolicy.RUNTIME)
@interface Cache {
}