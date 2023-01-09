package com.bondarenko.movieland.configuration.interceptorconfiguration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) {
        MDC.put("requestId", UUID.randomUUID().toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object object, Exception arg3) {
        log.info("Request is complete for user " + MDC.get("email"));
    }
}