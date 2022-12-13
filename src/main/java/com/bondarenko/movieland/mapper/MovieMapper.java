package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {

    List<MovieDto> moviesToMovieDtos(List<Movie> movies);

    MovieDto movieToMovieDto(Movie movie);
}