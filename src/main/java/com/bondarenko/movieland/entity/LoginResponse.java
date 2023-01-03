package com.bondarenko.movieland.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginResponse {
    String token;
    String nickname;
}