package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.LoginResponseDto;
import org.springframework.http.ResponseEntity;

public interface SecurityService {
    ResponseEntity<String> getAuthentication(String email, String password);

    LoginResponseDto getLoginResponse(String email, String password);
}