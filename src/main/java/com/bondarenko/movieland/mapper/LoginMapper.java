package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.service.dto.response.LoginDto;
import com.bondarenko.movieland.service.entity.response.Login;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper {
    LoginDto toLoginResponseDto(Login login);
}