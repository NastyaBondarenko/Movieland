package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.LoginDto;
import com.bondarenko.movieland.entity.Login;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper {
    LoginDto toLoginResponseDto(Login login);
}