package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.AbstractWebITest;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
public class MovieControllerITest extends AbstractWebITest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet("datasets/movie/dataset_movies.yml")
    @ExpectedDataSet("datasets/movie/dataset_movies.yml")
    @DisplayName("when Get All Movies with Correct Url then Ok Status Returned")
    void whenGetAllMovies_withCorrectUrl_thenOkStatusReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                [
                                   {
                                      "id":1,
                                      "nameRussian":"Побег из Шоушенка",
                                      "nameNative":"The Shawshank Redemption",
                                      "yearOfRelease":1994,
                                      "description":"Успешный банкир Энди Дюфрейн обвинен в убийстве",
                                      "rating":8.9,
                                      "price":123.45,
                                      "picturePath":"https://images.jpg",
                                      "votes":100
                                   },
                                   {
                                      "id":2,
                                      "nameRussian":"Зеленая миля",
                                      "nameNative":"The Green Mile",
                                      "yearOfRelease":1999,
                                      "description":"Обвиненный в страшном преступлении",
                                      "rating":8.9,
                                      "price":134.67,
                                      "picturePath":"https://images.jpg",
                                      "votes":100
                                   }
                                ]"""))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet("datasets/movie/dataset_get_by_genre.yml")
    @DisplayName("when Get Movie By Genre with Correct Url then Ok Status Returned")
    void whenGetMovieByGenre_withCorrectUrl_thenOkStatusReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                [
                                   {
                                      "id":1,
                                      "nameRussian":"Побег из Шоушенка",
                                      "nameNative":"The Shawshank Redemption",
                                      "yearOfRelease":1994,
                                      "description":"Успешный банкир Энди Дюфрейн обвинен в убийстве",
                                      "rating":8.9,
                                      "price":123.45,
                                      "picturePath":"https://images.jpg",
                                      "votes":100
                                   },
                                   {
                                      "id":3,
                                      "nameRussian":"Побег",
                                      "nameNative":"TheRedemption",
                                      "yearOfRelease":1994,
                                      "description":"Банкир",
                                      "rating":8.9,
                                      "price":123.45,
                                      "picturePath":"https://images.jpg",
                                      "votes":100
                                   }
                                ]"""))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet("datasets/movie/dataset_movies.yml")
    @DisplayName("when Get All Movies with Incorrect Url then Not Found Returned")
    void whenGetAllMovies_withIncorrectUrl_thenNotFoundReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DataSet("datasets/movie/dataset_get_by_genre.yml")
    @DisplayName("when Get Movie By Genre with Incorrect Url then Bad Request Return")
    void whenGetMovieByGenre_withIncorrectUrl_thenBadRequestReturn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/dd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DataSet("datasets/movie/dataset_get_by_genre.yml")
    @DisplayName("when Get Random Movies with Incorrect Url then Not Found Return")
    void whenGetRandomMovies_withIncorrectUrl_thenNotFoundReturn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/randoms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
