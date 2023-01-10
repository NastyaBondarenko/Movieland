package com.bondarenko.movieland.service;

import com.bondarenko.movieland.dto.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.dto.MovieRequestDto;
import com.bondarenko.movieland.entity.CurrencyType;
import com.bondarenko.movieland.entity.MovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll(MovieRequest movieRequest);

    List<MovieDto> findRandom();

    List<MovieDto> findByGenre(MovieRequest movieRequest);

    MovieDetailsDto findById(int movieId, CurrencyType currency);

    void add(MovieRequestDto movieDetailsDto);

    void update(MovieRequestDto movieRequestDto, int movieId);
}