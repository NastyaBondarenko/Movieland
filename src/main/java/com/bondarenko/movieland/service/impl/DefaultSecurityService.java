package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.configuration.JwtUtils;
import com.bondarenko.movieland.dto.LoginResponseDto;
import com.bondarenko.movieland.entity.LoginResponse;
import com.bondarenko.movieland.mapper.LoginMapper;
import com.bondarenko.movieland.service.SecurityService;
import com.bondarenko.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultSecurityService implements SecurityService {
    private final AuthenticationManager authenticationManager;
    private final LoginMapper loginMapper;

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<String> getAuthentication(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        final UserDetails user = userService.loadUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("error");

    }

    @Override
    public LoginResponseDto getLoginResponse(String email, String password) {
        String token = getAuthentication(email, password).getBody();
        String nickname = userService.getNickname(email);
        return loginMapper.toLoginResponseDto(LoginResponse.builder()
                .token(token)
                .nickname(nickname)
                .build());
    }
}