package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.AbstractWebITest;
import com.bondarenko.movieland.cache.GenreCache;
import com.bondarenko.movieland.entity.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GenreControllerITest extends AbstractWebITest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    GenreCache cache;

    @Test
    @DisplayName("when Get All Genres with Correct Url then Ok Status Returned")
    void whenGetGenres_withCorrectUrl_thenOkStatusReturned() throws Exception {
        Genre genreFirst = Genre.builder()
                .genreId(1)
                .name("драма")
                .build();

        Genre genreSecond = Genre.builder()
                .genreId(2)
                .name("криминал")
                .build();

        Genre genreThird = Genre.builder()
                .genreId(3)
                .name("комедия")
                .build();
        when(cache.getCachedGenre()).thenReturn(List.of(genreFirst, genreSecond, genreThird));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genre")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                [
                                   {
                                      "genreId":1,
                                      "name":"драма"
                                   },
                                   {
                                      "genreId":2,
                                      "name":"криминал"
                                   },
                                   {
                                      "genreId":3,
                                      "name":"комедия"
                                   }
                                ]"""))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when Get Genres with Incorrect Url then Not Found Returned")
    void whenGetGenres_withIncorrectUrl_thenNotFoundReturned() throws Exception {
        when(cache.getCachedGenre()).thenReturn(List.of(new Genre(1, "криминал")));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
