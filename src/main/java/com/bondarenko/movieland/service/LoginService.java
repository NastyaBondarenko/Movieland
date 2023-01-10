package com.bondarenko.movieland.service;

import com.bondarenko.movieland.service.dto.response.LoginDto;

public interface LoginService {
    LoginDto getLogin(String email, String password);
}