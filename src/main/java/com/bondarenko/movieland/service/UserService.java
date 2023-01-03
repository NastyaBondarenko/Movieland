package com.bondarenko.movieland.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByEmail(String email);

    String getNickname(String email);
}