package com.bondarenko.movieland.service.query;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.configuration.TestConfigurationToCountAllQueries;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.service.entity.common.SortDirection;
import com.bondarenko.movieland.service.entity.request.MovieRequest;
import com.bondarenko.movieland.service.impl.DefaultCountryService;
import com.bondarenko.movieland.service.impl.DefaultGenreService;
import com.bondarenko.movieland.service.impl.DefaultMovieService;
import com.bondarenko.movieland.service.impl.DefaultReviewService;
import com.github.database.rider.core.api.dataset.DataSet;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;

import java.util.List;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;

@Import(TestConfigurationToCountAllQueries.class)
@AutoConfigureMockMvc(addFilters = false)
public class ServiceQueryTest extends AbstractBaseITest {

    @Autowired
    private DefaultMovieService movieService;
    @Autowired
    private DefaultCountryService countryService;
    @Autowired
    private DefaultGenreService genreService;
    @Autowired
    private DefaultReviewService reviewService;

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("validate Queries after Find All Movies")
    void validateQueries_afterFindAllMovies() {
        SQLStatementCountValidator.reset();
        MovieRequest movieRequest = MovieRequest.builder()
                .price(SortDirection.ASC)
                .build();

        movieService.findAll(movieRequest);
        assertSelectCount(1);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_get_random.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("validate Queries after Find Random Movies")
    void validateQueries_afterFindRandomMovies() {
        SQLStatementCountValidator.reset();

        movieService.findRandom();
        assertSelectCount(1);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_get_by_genre.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("validate Queries after Find By Genre")
    void validateQueries_afterFindByGenre() {
        SQLStatementCountValidator.reset();
        MovieRequest movieRequest = MovieRequest.builder()
                .price(SortDirection.ASC)
                .genreId(1)
                .build();

        movieService.findByGenre(movieRequest);
        assertSelectCount(1);
    }

    @Test
    @DataSet("datasets/movie/dataset_add_movie.yml")
    @DisplayName("validate Queries After AddMovie")
    void validateQueriesAfterAddMovie() {
        SQLStatementCountValidator.reset();
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .nameRussian("Побег")
                .nameNative("The Shawshank Redemption")
                .picturePath("https://images-na")
                .description("Успешний банкир")
                .countryIds(List.of(1))
                .genreIds(List.of(1))
                .build();

        movieService.add(movieRequestDto);
        assertSelectCount(3);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("validate Queries After Update Movie")
    void validateQueriesAfterUpdateMovie() {
        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .nameRussian("Побег")
                .nameNative("The Shawshank Redemption")
                .picturePath("https://images-na")
                .countryIds(List.of(1))
                .genreIds(List.of(1))
                .build();

        movieService.update(movieRequestDto, 1);

        assertSelectCount(4);
    }

    @Test
    @DataSet(value = "datasets/country/dataset_countries.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("validate Queries after Find All Countries")
    void validateQueries_afterFindAllCountries() {
        SQLStatementCountValidator.reset();

        countryService.findAll();
        assertSelectCount(1);
    }

    @Test
    @DataSet(value = "datasets/genre/dataset_genres.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("validate Queries after Find All Genres")
    void validateQueries_afterFindAllGenres() {
        SQLStatementCountValidator.reset();

        genreService.findAll();
        assertSelectCount(0);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("validate Queries after Find All Genres")
    void validateQueries_afterFindGenreBiId() {
        SQLStatementCountValidator.reset();

        reviewService.findByMovieId(1);
        assertSelectCount(1);
    }
}
