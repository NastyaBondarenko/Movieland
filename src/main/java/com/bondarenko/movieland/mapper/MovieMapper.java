package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto movieToMovieDto(Movie movie);

    List<MovieDto> moviesToMovieDtos(List<Movie> movies);
}