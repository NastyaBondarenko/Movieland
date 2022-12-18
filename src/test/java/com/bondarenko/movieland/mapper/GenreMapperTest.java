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
    @DisplayName("Mapping Genre list to GenreDto list")
    void test_givenGenres_whenMapGenresToGenreDtos_thenReturnGenreDtos() {
        Genre genreFirst = Genre.builder()
                .genreId(1)
                .name("драма")
                .build();

        Genre genreSecond = Genre.builder()
                .genreId(2)
                .name("комедия")
                .build();

        Genre genreThird = Genre.builder()
                .genreId(3)
                .name("криминал")
                .build();

        List<GenreDto> genreDtos = genreMapper.genresToGenreDtos(List.of(genreFirst, genreSecond, genreThird));

        assertEquals(3, genreDtos.size());
        assertEquals("драма", genreDtos.get(0).getName());
        assertEquals("комедия", genreDtos.get(1).getName());
        assertEquals("криминал", genreDtos.get(2).getName());
    }

    @Test
    @DisplayName("Mapping Genre to GenreDto")
    void givenGenre_whenMapGenreToGenreDto_thenReturnGenreDto() {
        GenreDto genreDto = genreMapper.genreToGenreDto(new Genre(1, "драма"));

        assertEquals(1, genreDto.getGenreId());
        assertEquals("драма", genreDto.getName());
    }
}
