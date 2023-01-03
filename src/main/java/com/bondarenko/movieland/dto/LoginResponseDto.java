package com.bondarenko.movieland.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    String token;
    String nickname;
}
