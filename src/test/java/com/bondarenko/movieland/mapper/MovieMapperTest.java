package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieMapperTest {
    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @Test
    @DisplayName("Mapping Movie to MovieDto")
    void test_givenMovie_whenMapMovieToMovieDto_thenReturnMovieDto() {
        Movie movie = Movie.builder()
                .id(1)
                .price(30.00)
                .votes(2)
                .description("description")
                .nameNative("nativeName")
                .nameRussian("russianName")
                .picturePath("path")
                .rating(200.00)
                .build();
        MovieDto movieDto = movieMapper.movieToMovieDto(movie);

        assertEquals(1, movieDto.getId());
        assertEquals(200.00, movieDto.getRating());
        assertEquals(30.00, movieDto.getPrice());
        assertEquals("description", movieDto.getDescription());
        assertEquals("nativeName", movieDto.getNameNative());
        assertEquals("russianName", movieDto.getNameRussian());
        assertEquals("path", movieDto.getPicturePath());
        assertEquals(2, movieDto.getVotes());
    }

    @Test
    @DisplayName("Mapping Movies List  to MovieDtos list")
    void test_givenMovies_whenMapMoviesToMovieDtos_thenReturnMovieDtos() {
        Movie movie = Movie.builder()
                .id(1)
                .price(30.00)
                .votes(2)
                .description("description")
                .nameNative("nativeName")
                .nameRussian("russianName")
                .picturePath("path")
                .rating(200.00)
                .build();
        List<MovieDto> movieDtos = movieMapper.moviesToMovieDtos(List.of(movie, movie, movie));
        assertEquals(3, movieDtos.size());
    }
}
