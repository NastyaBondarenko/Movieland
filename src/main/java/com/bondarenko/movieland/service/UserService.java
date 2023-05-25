package com.bondarenko.movieland.service;

import com.bondarenko.movieland.entity.User;

public interface UserService {
    User findUserByEmail(String email);

    String getNickname(String email);
}