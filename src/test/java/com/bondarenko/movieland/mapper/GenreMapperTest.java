package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreMapperTest {
    private final GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    @Test
    @DisplayName("Mapping Genre to GenreDto")
    void test_givenGenre_whenMapGenreToGenreDto_thenReturnGenreDto() {
        Genre genre = Genre.builder()
                .genreId(1)
                .name("drama")
                .build();
        GenreDto genreDto = genreMapper.genreToGenreDto(genre);
        assertEquals(1, genreDto.getGenreId());
        assertEquals("drama", genreDto.getName());
    }

    @Test
    @DisplayName("Mapping Genre list to GenreDto list")
    void test_givenGenres_whenMapGenresToGenreDtos_thenReturnGenreDtos() {
        Genre genre = Genre.builder()
                .genreId(1)
                .name("drama")
                .build();

        List<GenreDto> genreDtos = genreMapper.genresToGenreDtos(List.of(genre, genre, genre));
        assertEquals(3, genreDtos.size());
    }
}
