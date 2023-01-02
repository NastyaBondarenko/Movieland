package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.dto.LoginRequest;
import com.bondarenko.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class LoginController {
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest request) {
        return userService.authenticates(request.getEmail(), request.getPassword());
    }
}