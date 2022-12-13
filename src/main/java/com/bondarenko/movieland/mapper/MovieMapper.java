package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public List<MovieDto> moviesToMovieDtos(List<Movie> movies) {
        return movies.stream()
                .map(this::movieToMovieDto)
                .toList();
    }

    public MovieDto movieToMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setPrice(movie.getPrice());
        movieDto.setNameNative(movie.getNameNative());
        movieDto.setNameRussian(movie.getNameRussian());
        movieDto.setDescription(movie.getDescription());
        movieDto.setRating(movie.getRating());
        movieDto.setYearOfRelease(movie.getYearOfRelease());
        movieDto.setPicturePath(movie.getPicturePath());
        movieDto.setVotes(movie.getVotes());

        return movieDto;
    }
}