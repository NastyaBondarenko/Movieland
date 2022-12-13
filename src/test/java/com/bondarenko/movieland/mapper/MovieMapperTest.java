package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieMapperTest {
    private MovieMapper movieMapper = new MovieMapper();

    @Test
    @DisplayName("Mapping Movie to MovieDto")
    void test_givenMovie_whenMapMovieToMovieDto_thenReturnMovieDto() {
        Movie mockMovie = mock(Movie.class);

        when(mockMovie.getId()).thenReturn(1);
        when(mockMovie.getPrice()).thenReturn(30.00);
        when(mockMovie.getVotes()).thenReturn(2);
        when(mockMovie.getDescription()).thenReturn("description");
        when(mockMovie.getNameNative()).thenReturn("nativeName");
        when(mockMovie.getNameRussian()).thenReturn("getRussianName");
        when(mockMovie.getPicturePath()).thenReturn("path");
        when(mockMovie.getRating()).thenReturn(200.00);

        MovieDto movieDto = movieMapper.movieToMovieDto(mockMovie);

        assertEquals(1, movieDto.getId());
        assertEquals(200.00, movieDto.getRating());
        assertEquals(30.00, movieDto.getPrice());
        assertEquals("description", movieDto.getDescription());
        assertEquals("nativeName", movieDto.getNameNative());
        assertEquals("getRussianName", movieDto.getNameRussian());
        assertEquals("path", movieDto.getPicturePath());
        assertEquals(2, movieDto.getVotes());

    }

//    @Test
//    @DisplayName("Mapping Movie list to MovieDto list")
//    void test_givenMovies_whenMapGenresToMovieDtos_thenReturnMovieDtos() {
//        Genre mockProduct = mock(Genre.class);
//
//        when(mockProduct.getName()).thenReturn("drama");
//        when(mockProduct.getGenreId()).thenReturn(5);
//
//        GenreMapper spyClientMapper = spy(movieMapper);
//        List<GenreDto> addressOrderDtoList = spyClientMapper
//                .genresToGenreDtos(List.of(mockProduct, mockProduct, mockProduct));
//        assertEquals(3, addressOrderDtoList.size());
//        verify(spyClientMapper).genresToGenreDtos(anyList());
//    }
}
