package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.entity.User;
import com.bondarenko.movieland.exceptions.UserNotFoundException;
import com.bondarenko.movieland.repository.UserRepository;
import com.bondarenko.movieland.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public String getNickname(String email) {
        return findUserByEmail(email).getNickname();
    }
}