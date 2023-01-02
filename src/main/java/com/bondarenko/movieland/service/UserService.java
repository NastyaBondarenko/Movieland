package com.bondarenko.movieland.service;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity authenticates(String email, String password);
}