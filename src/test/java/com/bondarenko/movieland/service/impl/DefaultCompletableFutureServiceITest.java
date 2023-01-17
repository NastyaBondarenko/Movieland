package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class DefaultCompletableFutureServiceITest extends AbstractBaseITest {
    @Autowired
    private DefaultCompletableFutureService completableFutureService;

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Enrich By CountryDtos then Completable Future with Enriched MovieDetailsDto Return")
    void whenEnrichByCountryDtos_thenCompletableFuture_withEnrichedMovieDetailsDtoReturn() throws ExecutionException, InterruptedException {
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();

        CompletableFuture<MovieDetailsDto> completableFuture =
                completableFutureService.enrichByCountryDtos(2, movieDetailsDto);
        Set<CountryDto> actualCountryDtos = completableFuture.get().getCountries();

        String countryDtoName = actualCountryDtos.stream().findFirst().get().getName();
        int countryDtoNaId = actualCountryDtos.stream().findFirst().get().getId();

        assertEquals(1, actualCountryDtos.size());
        assertEquals(1, countryDtoNaId);
        assertEquals("USA", countryDtoName);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Enrich By CountryDtos then Completable Future enriched By CountryDtos")
    void whenEnrichByGenreDtos_thenCompletableFuture_withEnrichedMovieDetailsDtoReturn() throws ExecutionException, InterruptedException {
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();

        CompletableFuture<MovieDetailsDto> completableFuture =
                completableFutureService.enrichByGenreDtos(2, movieDetailsDto);
        Set<GenreDto> actualGenreDtos = completableFuture.get().getGenres();
        String genreDtoName = actualGenreDtos.stream().findFirst().get().getName();
        int genreDtoNaId = actualGenreDtos.stream().findFirst().get().getId();

        assertEquals(1, actualGenreDtos.size());
        assertEquals(2, genreDtoNaId);
        assertEquals("криминал", genreDtoName);
    }


    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Enrich By ReviewDtos then CompletableFuture with Enriched MovieDetailsDto Return")
    void whenEnrichByReviewDtos_thenCompletableFuture_withEnrichedMovieDetailsDtoReturn() throws ExecutionException, InterruptedException {
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();

        CompletableFuture<MovieDetailsDto> completableFuture =
                completableFutureService.enrichByReviewDtos(2, movieDetailsDto);
        Set<ReviewDto> actualReviewDtos = completableFuture.get().getReviews();

        assertEquals(2, actualReviewDtos.size());
    }
}