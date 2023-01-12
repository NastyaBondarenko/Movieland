package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.service.entity.common.SortDirection;
import com.bondarenko.movieland.service.entity.request.MovieRequest;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class DefaultMovieServiceITest extends AbstractBaseITest {
    @Autowired
    DefaultMovieService movieService;

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Find All then Movie Dtos Returned")
    public void whenFindAll_thenMovieDtosReturned() {
        MovieRequest movieRequest = MovieRequest.builder()
                .price(SortDirection.ASC)
                .build();

        List<MovieDto> movieDtos = movieService.findAll(movieRequest);
        MovieDto movieDtoFirst = movieDtos.get(0);
        assertEquals(1, movieDtoFirst.getId());
        assertEquals(1994, movieDtoFirst.getYearOfRelease());
        assertEquals("Успешный банкир Энди Дюфрейн обвинен в убийстве", movieDtoFirst.getDescription());
        assertEquals("https://images.jpg", movieDtoFirst.getPicturePath());
        assertEquals("Побег из Шоушенка", movieDtoFirst.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDtoFirst.getNameNative());
        assertEquals(8.9, movieDtoFirst.getRating());
        assertEquals(123.45, movieDtoFirst.getPrice());
        assertEquals(100, movieDtoFirst.getVotes());
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_get_random.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Find Random then Three Random Movies Return")
    public void whenFindRandom_thenThreeRandomMoviesReturn() {
        List<MovieDto> randomMovies = movieService.findRandom();

        assertEquals(3, randomMovies.size());
        assertNotNull(randomMovies);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Find By Genre then Correct Movie Dtos Returned")
    public void whenFindByGenre_thenCorrectMovieDtosReturned() {
        MovieRequest movieRequest = MovieRequest.builder()
                .price(SortDirection.ASC)
                .genreId(1)
                .build();

        List<MovieDto> movieDtos = movieService.findByGenre(movieRequest);
        MovieDto movieDtoFirst = movieDtos.get(0);
        assertEquals(1, movieDtoFirst.getId());
        assertEquals(1994, movieDtoFirst.getYearOfRelease());
        assertEquals("Успешный банкир Энди Дюфрейн обвинен в убийстве", movieDtoFirst.getDescription());
        assertEquals("https://images.jpg", movieDtoFirst.getPicturePath());
        assertEquals("Побег из Шоушенка", movieDtoFirst.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDtoFirst.getNameNative());
        assertEquals(8.9, movieDtoFirst.getRating());
        assertEquals(123.45, movieDtoFirst.getPrice());
        assertEquals(100, movieDtoFirst.getVotes());
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Find Movie By Id then Correct MovieDetailsDto Returned")
    public void whenFindMovieById_thenCorrectMovieDetailsDtoReturned() {

        MovieDetailsDto movieDetailsDto = movieService.findById(1, null);

        assertEquals(1, movieDetailsDto.getId());
        assertEquals("Успешный банкир Энди Дюфрейн обвинен в убийстве", movieDetailsDto.getDescription());
        assertEquals("https://images.jpg", movieDetailsDto.getPicturePath());
        assertEquals("Побег из Шоушенка", movieDetailsDto.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDetailsDto.getNameNative());
        assertEquals(8.9, movieDetailsDto.getRating());
        assertEquals(123.45, movieDetailsDto.getPrice());
        assertEquals(100, movieDetailsDto.getVotes());

        Set<CountryDto> countrieDtos = movieDetailsDto.getCountries();
        CountryDto countrieDto = countrieDtos.stream().findFirst().get();
        assertEquals(1, countrieDtos.size());
        assertEquals("USA", countrieDto.getName());
        assertEquals(1, countrieDto.getId());

        Set<GenreDto> genreDtos = movieDetailsDto.getGenres();
        GenreDto genreDto = genreDtos.stream().findFirst().get();
        assertEquals(1, genreDtos.size());
        assertEquals("драма", genreDto.getName());
        assertEquals(1, genreDto.getId());
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_add_movie.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @ExpectedDataSet("datasets/movie/dataset_expected_add_movie.yml")
    @DisplayName("when Find Movie By Id then Correct MovieDetailsDto Returned")
    public void whenFindMovideById_thenCorrectMovieDetailsDtoReturned() {
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
        MovieDto movieDto = movieService.add(movieRequestDto);

        assertEquals(1, movieDto.getId());
        assertEquals("Успешный банкир Энди Дюфрейн", movieDto.getDescription());
        assertEquals("https://images-na", movieDto.getPicturePath());
        assertEquals("Побег", movieDto.getNameRussian());
        assertEquals("The Shawshank Redemption", movieDto.getNameNative());
        assertEquals(0.0, movieDto.getRating());
        assertEquals(123.45, movieDto.getPrice());
        assertEquals(0, movieDto.getVotes());
    }
}
