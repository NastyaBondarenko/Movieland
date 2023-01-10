package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.LoginDto;
import com.bondarenko.movieland.entity.Login;
import com.bondarenko.movieland.exceptions.UserNotFoundException;
import com.bondarenko.movieland.mapper.LoginMapper;
import com.bondarenko.movieland.service.LoginService;
import com.bondarenko.movieland.service.UserService;
import com.bondarenko.movieland.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultLoginService implements LoginService {
    private final AuthenticationManager authenticationManager;
    private SecurityService securityService;
    private final LoginMapper loginMapper;
    private final UserService userService;

    @Override
    public LoginDto getLogin(String email, String password) {
        String token = getAuthentication(email, password).getBody();
        String nickname = userService.getNickname(email);
        return loginMapper.toLoginResponseDto(new Login(token, nickname));
    }

    private ResponseEntity<String> getAuthentication(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = securityService.generateToken(email);
        return !token.isEmpty() ? ResponseEntity.ok(token) : ResponseEntity.status(400)
                .body(new UserNotFoundException(email).getMessage());
    }
}