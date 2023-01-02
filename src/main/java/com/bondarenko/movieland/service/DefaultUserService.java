package com.bondarenko.movieland.service;

import com.bondarenko.movieland.configuration.JwtUtils;
import com.bondarenko.movieland.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity authenticates(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        final UserDetails user = userRepository.findUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("error");

    }
}