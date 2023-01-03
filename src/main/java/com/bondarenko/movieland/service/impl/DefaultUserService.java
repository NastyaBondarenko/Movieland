package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.exceptions.UserNotFoundException;
import com.bondarenko.movieland.repository.UserRepository;
import com.bondarenko.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByEmail(String email) {
        com.bondarenko.movieland.entity.User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        String password = user.getPassword();
        return new User(email, password, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public String getNickname(String email) {
        com.bondarenko.movieland.entity.User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return user.getNickname();
    }
}