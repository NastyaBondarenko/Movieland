package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.AbstractWebITest;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.repository.GenreRepository;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
public class ControllerITest extends AbstractWebITest {

    @MockBean
    private GenreRepository genreRepository;

    @Test
    @DataSet("datasets/movie/dataset_movies.yml")
    @ExpectedDataSet("datasets/movie/dataset_movies.yml")
    @DisplayName("when Get All Movies with Correct Url then Ok Status Returned")
    void whenGetAllMovies_withCorrectUrl_thenOkStatusReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
                        .contentType(MediaType.APPLICATION_JSON))
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
    @DataSet("datasets/movie/dataset_movies.yml")
    @DisplayName("when Get Movie By Id then Ok Status Returned")
    void whenGetMovieById_thenOkStatusReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("""
                                {
                                   "id":2,
                                   "nameRussian":"Зеленая миля",
                                   "nameNative":"The Green Mile",
                                   "yearOfRelease":"1999",
                                   "description":"Обвиненный в страшном преступлении",
                                   "rating":8.9,
                                   "price":134.67,
                                   "picturePath":"https://images.jpg",
                                   "votes":100,
                                   "genres":[{
                                         "id":2,
                                         "name":"криминал"
                                      }
                                   ],
                                   "countries":[{
                                         "id":1,
                                         "name":"USA"
                                      }
                                   ],
                                   "reviews":[{
                                         "id":1,
                                         "description":"Гениальное кино!",
                                         "user":{
                                            "id":1,
                                            "nickname":"Джесси Паттерсон"
                                         }
                                      },
                                      {
                                         "id":2,
                                         "description":"Очень хороший фильм!",
                                         "user":{
                                            "id":1,
                                            "nickname":"Джесси Паттерсон"
                                         }
                                      }
                                   ]
                                }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @ExpectedDataSet("datasets/movie/dataset_update_movie.yml")
    @DisplayName("when Update Movie then Updated Movie and Ok Status Returned")
    void whenUpdateMovie_thenUpdatedMovie_andOkStatusReturned() throws Exception {
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .nameRussian("Побег")
                .nameNative("The Shawshank Redemption")
                .picturePath("https://images-na")
                .countryIds(List.of(1))
                .genreIds(List.of(1))
                .build();
        mockMvc.perform(put("/api/v1/movie/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestDto)))
                .andExpect(content()
                        .json("""
                                {
                                "id":2,
                                "nameRussian":"Побег",
                                "nameNative":"The Shawshank Redemption",
                                "yearOfRelease":1999,
                                "description":"Обвиненный в страшном преступлении",
                                "rating":8.9,
                                "price":134.67,
                                "picturePath":"https://images-na",
                                "votes":100
                                }
                                 """))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_add_movie.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @ExpectedDataSet("datasets/movie/dataset_expected_add_movie.yml")
    @DisplayName("when Add Movie then Added Car and Ok Status Returned")
    void whenAddMovie_thenAddedCar_andOkStatusReturned() throws Exception {
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .nameRussian("Побег")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(1994)
                .price(123.45)
                .description("Успешный банкир Энди Дюфрейн")
                .picturePath("https://images-na")
                .countryIds(List.of(2))
                .genreIds(List.of(2))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestDto)))
                .andExpect(content()
                        .json("""                        
                                {
                                "id":1,
                                "nameRussian":"Побег",
                                "nameNative":"The Shawshank Redemption",
                                "yearOfRelease":1994,
                                "description":"Успешный банкир Энди Дюфрейн",
                                "rating":0.0,
                                "price":123.45,
                                "picturePath":"https://images-na",
                                "votes":0
                                }
                                 """))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet("datasets/movie/dataset_countries.yml")
    @ExpectedDataSet("datasets/movie/dataset_countries.yml")
    @DisplayName("when Get All Countries with Correct Url then Ok Status Returned")
    void whenGetAllCountries_withCorrectUrl_thenOkStatusReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/country")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("""
                                [
                                   {
                                      "id":1,
                                      "name":"США"
                                   },
                                   {
                                      "id":2,
                                      "name":"Франция"
                                   },
                                   {
                                      "id":3,
                                      "name":"Великобритания"
                                   },
                                   {
                                      "id":4,
                                      "name":"Италия"
                                   }
                                ]"""))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when Get All Genres with Correct Url then Ok Status Returned")
    void whenGetGenres_withCorrectUrl_thenOkStatusReturned() throws Exception {
        Genre genreFirst = new Genre(1, "драма");
        Genre genreSecond = new Genre(2, "криминал");
        Genre genreThird = new Genre(3, "комедия");

        when(genreRepository.findAll()).thenReturn(List.of(genreFirst, genreSecond, genreThird));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genre")
                        .contentType(MediaType.APPLICATION_JSON))
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
//
//    @Test
//    @DataSet("datasets/common/car/dataset_reviews.yml")
//    @DisplayName("when Add Review and Ok Status Returned")
//    void whenAddReview_andOkStatusReturned() throws Exception {
//        ReviewRequestDto reviewDto = ReviewRequestDto.builder()
//                .movieId(1)
//                .description("Гениальное кино!")
//                .build();
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/review")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(reviewDto)))
//                .andExpect(content()
//                        .json("""
//
//                                """))
//                .andExpect(status().isOk());
//    }


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
//    @DisplayName("when Get All Movies with Incorrect Url then Not Found Returned")
//    void whenGetAllMovies_withIncorrectUrl_thenNotFoundReturned() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("when Get Movie By Genre with Incorrect Url then Bad Request Return")
//    void whenGetMovieByGenre_withIncorrectUrl_thenBadRequestReturn() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/dd")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("when Get Random Movies with Incorrect Url then Not Found Return")
//    void whenGetRandomMovies_withIncorrectUrl_thenNotFoundReturn() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/randoms")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
}
