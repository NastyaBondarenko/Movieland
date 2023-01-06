package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.LoginRequestDto;
import com.bondarenko.movieland.dto.LoginResponseDto;
import com.bondarenko.movieland.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class SecurityController {
    private final SecurityService securityService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public LoginResponseDto getAuthentication(@RequestBody LoginRequestDto request) {
        log.info("Successful signing up for user " + request.getEmail());
        return securityService.getLoginResponse(request.getEmail(), request.getPassword());
    }
}