package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.entity.common.TaskResult;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class DefaultFutureServiceITest extends AbstractBaseITest {
    @Autowired
    private DefaultFutureService futureService;

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test get futureCountryDtos and return it")
    void whenGetFutureCountryDtos_thenFutureReturned() throws ExecutionException, InterruptedException {

        Future<TaskResult> future =
                futureService.getFutureCountryDtos(2, new TaskResult());
        Set<CountryDto> actualCountryDtos = future.get().getCountryDtos();

        String countryDtoName = actualCountryDtos.stream().findFirst().get().getName();
        int countryDtoNaId = actualCountryDtos.stream().findFirst().get().getId();

        assertEquals(1, actualCountryDtos.size());
        assertEquals(1, countryDtoNaId);
        assertEquals("USA", countryDtoName);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test get futureGenreDtos and return it")
    void whenGetFutureGenreDtos_thenFutureReturned() throws ExecutionException, InterruptedException {

        Future<TaskResult> future =
                futureService.getFutureGenreDtos(2, new TaskResult());
        Set<GenreDto> actualGenreDtos = future.get().getGenreDtos();
        String genreDtoName = actualGenreDtos.stream().findFirst().get().getName();
        int genreDtoNaId = actualGenreDtos.stream().findFirst().get().getId();

        assertEquals(1, actualGenreDtos.size());
        assertEquals(2, genreDtoNaId);
        assertEquals("криминал", genreDtoName);
    }


    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test get futureReviewDtos and return it")
    void whenGetFutureReviewDtos_thenFutureReturned() throws ExecutionException, InterruptedException {

        Future<TaskResult> completableFuture =
                futureService.getFutureReviewDtos(2, new TaskResult());
        Set<ReviewDto> actualReviewDtos = completableFuture.get().getReviewDtos();

        assertEquals(2, actualReviewDtos.size());
    }
}