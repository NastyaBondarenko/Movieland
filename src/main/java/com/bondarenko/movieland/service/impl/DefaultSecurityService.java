package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.LoginDto;
import com.bondarenko.movieland.entity.Login;
import com.bondarenko.movieland.exceptions.UserNotFoundException;
import com.bondarenko.movieland.mapper.LoginMapper;
import com.bondarenko.movieland.service.SecurityService;
import com.bondarenko.movieland.service.UserService;
import com.bondarenko.movieland.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultSecurityService implements SecurityService {
    private final AuthenticationManager authenticationManager;
    private final LoginMapper loginMapper;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private JwtSecurityService jwtSecurityService;


    @Override
    public LoginDto getLogin(String email, String password) {
        String token = getAuthentication(email, password).getBody();
        String nickname = userService.getNickname(email);
        return loginMapper.toLoginResponseDto(Login.builder()
                .token(token)
                .nickname(nickname)
                .build());
    }

    private ResponseEntity<String> getAuthentication(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        final UserDetails user = jwtSecurityService.loadUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body(new UserNotFoundException(email).getMessage());
    }
}