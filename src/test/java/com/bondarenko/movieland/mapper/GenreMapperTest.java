package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreMapperTest {
    @Autowired
    private GenreMapper genreMapper;

//    @BeforeAll
//    void setup() {
////        genreMapper = new GenreMapper();
//        Genre mockProduct = mock(Genre.class);
//        when(mockProduct.getName()).thenReturn("5");
//        when(mockProduct.getGenreId()).thenReturn(5);
//    }

    @Test
    @DisplayName("Mapping Order to AddressDto")
    void test_givenOrder_whenMapAddressOrder_thenReturnAddressOrderDto() {
        Genre mockProduct = mock(Genre.class);

        Genre genred = Genre.builder()

                .name("1")
                .genreId(1)
                .build();
        when(genred.getName()).thenReturn("5");
        when(genred.getGenreId()).thenReturn(5);
        GenreDto addressOrderDto = genreMapper.genreToGenreDto(mockProduct);

        assertEquals("1", addressOrderDto.getName());
        assertEquals(1, addressOrderDto.getGenreId());
    }


}
