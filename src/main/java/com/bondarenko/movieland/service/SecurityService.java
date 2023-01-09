package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.LoginDto;
import org.springframework.http.ResponseEntity;

public interface SecurityService {
    ResponseEntity<String> getAuthentication(String email, String password);

    LoginDto getLogin(String email, String password);
}