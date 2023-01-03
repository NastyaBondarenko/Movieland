package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.LoginResponseDto;
import com.bondarenko.movieland.entity.LoginResponse;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper {
    LoginResponseDto toLoginResponseDto(LoginResponse loginResponse);
}