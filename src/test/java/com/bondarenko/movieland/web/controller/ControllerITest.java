//package com.bondarenko.movieland.web.controller;
//
//import com.bondarenko.movieland.AbstractWebITest;
//import com.bondarenko.movieland.entity.Genre;
//import com.bondarenko.movieland.repository.GenreRepository;
//import com.github.database.rider.core.api.dataset.DataSet;
//import com.github.database.rider.core.api.dataset.ExpectedDataSet;
//import com.github.database.rider.spring.api.DBRider;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@DBRider
//public class ControllerITest extends AbstractWebITest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GenreRepository genreRepository;
//
//    @Test
//    @DataSet("datasets/movie/dataset_movies.yml")
//    @ExpectedDataSet("datasets/movie/dataset_movies.yml")
//    @DisplayName("when Get All Movies with Correct Url then Ok Status Returned")
//    void whenGetAllMovies_withCorrectUrl_thenOkStatusReturned() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json("""
//                                [
//                                   {
//                                      "id":1,
//                                      "nameRussian":"Побег из Шоушенка",
//                                      "nameNative":"The Shawshank Redemption",
//                                      "yearOfRelease":1994,
//                                      "description":"Успешный банкир Энди Дюфрейн обвинен в убийстве",
//                                      "rating":8.9,
//                                      "price":123.45,
//                                      "picturePath":"https://images.jpg",
//                                      "votes":100
//                                   },
//                                   {
//                                      "id":2,
//                                      "nameRussian":"Зеленая миля",
//                                      "nameNative":"The Green Mile",
//                                      "yearOfRelease":1999,
//                                      "description":"Обвиненный в страшном преступлении",
//                                      "rating":8.9,
//                                      "price":134.67,
//                                      "picturePath":"https://images.jpg",
//                                      "votes":100
//                                   }
//                                ]"""))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("when Get All Genres with Correct Url then Ok Status Returned")
//    void whenGetGenres_withCorrectUrl_thenOkStatusReturned() throws Exception {
//        Genre genreFirst = Genre.builder()
//                .id(1)
//                .name("драма")
//                .build();
//
//        Genre genreSecond = Genre.builder()
//                .id(2)
//                .name("криминал")
//                .build();
//
//        Genre genreThird = Genre.builder()
//                .id(3)
//                .name("комедия")
//                .build();
//        when(genreRepository.findAll()).thenReturn(List.of(genreFirst, genreSecond, genreThird));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genre")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json("""
//                                [
//                                   {
//                                      "id":1,
//                                      "name":"драма"
//                                   },
//                                   {
//                                      "id":2,
//                                      "name":"криминал"
//                                   },
//                                   {
//                                      "id":3,
//                                      "name":"комедия"
//                                   }
//                                ]"""))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("when Get Genres with Incorrect Url then Not Found Returned")
//    void whenGetGenres_withIncorrectUrl_thenNotFoundReturned() throws Exception {
//        when(genreRepository.findAll()).thenReturn(List.of(new Genre(1, "криминал")));
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genres")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DataSet("datasets/movie/dataset_movies.yml")
//    @DisplayName("when Get All Movies with Incorrect Url then Not Found Returned")
//    void whenGetAllMovies_withIncorrectUrl_thenNotFoundReturned() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DataSet("datasets/movie/dataset_get_by_genre.yml")
//    @DisplayName("when Get Movie By Genre with Incorrect Url then Bad Request Return")
//    void whenGetMovieByGenre_withIncorrectUrl_thenBadRequestReturn() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/dd")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DataSet("datasets/movie/dataset_get_by_genre.yml")
//    @DisplayName("when Get Random Movies with Incorrect Url then Not Found Return")
//    void whenGetRandomMovies_withIncorrectUrl_thenNotFoundReturn() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/randoms")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//}
