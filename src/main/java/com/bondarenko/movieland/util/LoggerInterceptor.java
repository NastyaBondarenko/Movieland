package com.bondarenko.movieland.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        Principal principal = request.getUserPrincipal();
        String user = principal == null ? "guest" : principal.getName();
        MDC.put("user", user);
        return true;
    }
}