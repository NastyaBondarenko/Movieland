package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.entity.User;
import com.bondarenko.movieland.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class JwtSecurityService {
    private UserService userService;

    public UserDetails loadUserByEmail(String email) {
        String password = userService.findUserByEmail(email).getPassword();
        User.UserRole userRole = userService.findUserByEmail(email).getRole();
        int userId = userService.findUserByEmail(email).getId();
        MDC.put("userId", String.valueOf(userId));
        MDC.put("email", email);
        return new org.springframework.security.core.userdetails.User(email, password, Collections.singleton(new SimpleGrantedAuthority(userRole.getName())));
    }
}