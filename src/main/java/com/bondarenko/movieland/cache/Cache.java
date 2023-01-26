package com.bondarenko.movieland.cache;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Primary
@Component
@Retention(RetentionPolicy.RUNTIME)
@interface Cache {
}