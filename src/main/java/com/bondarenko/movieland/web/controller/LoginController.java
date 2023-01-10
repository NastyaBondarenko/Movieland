package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.LoginDto;
import com.bondarenko.movieland.dto.LoginRequestDto;
import com.bondarenko.movieland.service.LoginService;
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
public class LoginController {
    private final LoginService loginService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public LoginDto getLogin(@RequestBody LoginRequestDto request) {
        log.info("Successful signing up for user " + request.getEmail());
        return loginService.getLogin(request.getEmail(), request.getPassword());
    }
}