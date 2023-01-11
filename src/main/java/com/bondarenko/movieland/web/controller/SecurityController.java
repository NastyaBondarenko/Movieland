package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.service.dto.response.LoginDto;
import com.bondarenko.movieland.service.dto.request.LoginRequestDto;
import com.bondarenko.movieland.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class SecurityController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginDto getLogin(@RequestBody LoginRequestDto request) {
        log.info("Successful signing up for user {} ", request.getEmail());
        return loginService.getLogin(request.getEmail(), request.getPassword());
    }
}