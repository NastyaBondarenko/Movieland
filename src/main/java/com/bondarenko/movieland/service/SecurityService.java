package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.LoginDto;

public interface SecurityService {
    LoginDto getLogin(String email, String password);
}