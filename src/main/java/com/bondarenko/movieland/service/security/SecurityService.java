package com.bondarenko.movieland.service.security;

import com.bondarenko.movieland.service.UserService;
import com.bondarenko.movieland.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class SecurityService {
    private PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private UserService userService;

    public UserDetails loadUserByEmail(String email) {
        String password = userService.findUserByEmail(email).getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        String userRoleName = userService.findUserByEmail(email).getRole().getName();
        MDC.put("email", email);
        return new User(email, encodedPassword, Collections.singleton(new SimpleGrantedAuthority(userRoleName)));
    }

    public String generateToken(String email) {
        final UserDetails user = loadUserByEmail(email);
        return jwtUtils.generateToken(user);
    }
}