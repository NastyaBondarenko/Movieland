package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.AbstractWebITest;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.repository.GenreRepository;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class ControllerITest extends AbstractWebITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    @DisplayName("when Get All Genres with Correct Url then Ok Status Returned")
    void whenGetGenres_withCorrectUrl_thenOkStatusReturned() throws Exception {
        Genre genreFirst = new Genre(1, "драма");
        Genre genreSecond = new Genre(2, "криминал");
        Genre genreThird = new Genre(3, "комедия");

        when(genreRepository.findAll()).thenReturn(List.of(genreFirst, genreSecond, genreThird));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genre")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                [
                                   {
                                      "id":1,
                                      "name":"драма"
                                   },
                                   {
                                      "id":2,
                                      "name":"криминал"
                                   },
                                   {
                                      "id":3,
                                      "name":"комедия"
                                   }
                                ]"""))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when Get Genres with Incorrect Url then Not Found Returned")
    void whenGetGenres_withIncorrectUrl_thenNotFoundReturned() throws Exception {
        when(genreRepository.findAll()).thenReturn(List.of(new Genre(1, "криминал")));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genres")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("when Get All Movies with Incorrect Url then Not Found Returned")
    void whenGetAllMovies_withIncorrectUrl_thenNotFoundReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("when Get Movie By Genre with Incorrect Url then Bad Request Return")
    void whenGetMovieByGenre_withIncorrectUrl_thenBadRequestReturn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/dd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("when Get Random Movies with Incorrect Url then Not Found Return")
    void whenGetRandomMovies_withIncorrectUrl_thenNotFoundReturn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/randoms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
