package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.LoginRequest;
import com.bondarenko.movieland.dto.LoginResponseDto;
import com.bondarenko.movieland.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class LoginController {
    private final SecurityService securityService;

    @PostMapping("/login")
    public LoginResponseDto authenticate(@RequestBody LoginRequest request) {
        return securityService.getLoginResponse(request.getEmail(), request.getPassword());
    }
}