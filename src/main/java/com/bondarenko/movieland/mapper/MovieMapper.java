package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MovieMapper {

    List<MovieDto> toMovieDtos(List<Movie> movies);

    @Mapping(target = "yearOfRelease", expression = "java(movie.getYearOfRelease().getYear())")
    MovieDto toMovieDto(Movie movie);
}