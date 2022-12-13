package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class GenreMapperTest {
    private GenreMapper genreMapper = new GenreMapper();

    @Test
    @DisplayName("Mapping Genre to GenreDto")
    void test_givenGenre_whenMapGenreToGenreDto_thenReturnGenreDto() {
        Genre mockGenre = mock(Genre.class);

        when(mockGenre.getName()).thenReturn("drama");
        when(mockGenre.getGenreId()).thenReturn(5);
        GenreDto genreDto = genreMapper.genreToGenreDto(mockGenre);

        assertEquals("drama", genreDto.getName());
        assertEquals(5, genreDto.getGenreId());
    }

    @Test
    @DisplayName("Mapping Genre list to GenreDto list")
    void test_givenGenres_whenMapGenresToGenreDtos_thenReturnGenreDtos() {
        Genre mockProduct = mock(Genre.class);

        when(mockProduct.getName()).thenReturn("drama");
        when(mockProduct.getGenreId()).thenReturn(5);

        GenreMapper spyClientMapper = spy(genreMapper);
        List<GenreDto> addressOrderDtoList = spyClientMapper
                .genresToGenreDtos(List.of(mockProduct, mockProduct, mockProduct));
        assertEquals(3, addressOrderDtoList.size());
        verify(spyClientMapper).genresToGenreDtos(anyList());
    }
}
