package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.dto.MovieDtoShort;
import com.bondarenko.movieland.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MovieMapper {

    List<MovieDto> toMovieDtos(List<Movie> movies);

    @Mapping(target = "yearOfRelease", expression = "java(movie.getYearOfRelease().getYear())")
    MovieDto toMovieDto(Movie movie);

    @Mapping(target = "reviews", ignore = true)
    MovieDetailsDto toMovieDetailsDto(Movie movie);

    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "year")
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "votes", ignore = true)
    Movie toMovie(MovieDtoShort movieDtoShort);

    @Named("year")
    default LocalDate mapYearToLocalDate(int yearOfRelease) {
        return LocalDate.of(yearOfRelease, 1, 1);
    }
}