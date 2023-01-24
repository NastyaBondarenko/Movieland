package com.bondarenko.movieland.service;

import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.service.entity.common.CurrencyType;
import com.bondarenko.movieland.service.entity.request.MovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll(MovieRequest movieRequest);

    List<MovieDto> findRandom();

    List<MovieDto> findByGenre(MovieRequest movieRequest);

    MovieDetailsDto findById(int movieId, CurrencyType currency);

    MovieDto add(MovieRequestDto movieDetailsDto);

    MovieDetailsDto update(MovieRequestDto movieRequestDto, int movieId);
}