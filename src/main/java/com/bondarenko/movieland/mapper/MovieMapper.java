package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MovieMapper {

    List<MovieDto> toMovieDtos(List<Movie> movies);

    @Mapping(target = "yearOfRelease", expression = "java(movie.getYearOfRelease().getYear())")
    MovieDto toMovieDto(Movie movie);

    MovieDetailsDto toMovieDetailsDto(Movie movie);

    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "year")
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "votes", ignore = true)
    Movie toMovie(MovieRequestDto movieRequestDto);

    @Mapping(target = "yearOfRelease", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    Movie update(@MappingTarget Movie movie, MovieRequestDto movieRequestDto);

    @Named("year")
    default LocalDate mapYearToLocalDate(int yearOfRelease) {
        return LocalDate.of(yearOfRelease, 1, 1);
    }
}