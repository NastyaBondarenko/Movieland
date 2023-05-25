package com.bondarenko.movieland.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {
    UserDetails loadUserByEmail(String email);

    String generateToken(String email);
}