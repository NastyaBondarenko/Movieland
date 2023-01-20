package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.entity.common.EnrichmentTaskResult;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class EnrichmentFutureServiceITest extends AbstractBaseITest {
    @Autowired
    private EnrichmentFutureService futureService;

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test get futureCountryDtos")
    void whenGetFutureCountryDtos_thenFutureReturned() throws ExecutionException, InterruptedException, TimeoutException {

        Future<EnrichmentTaskResult> future = futureService.getFutureCountryDtos(2, new EnrichmentTaskResult());
        Set<CountryDto> actualCountryDtos = future.get(5, TimeUnit.SECONDS).getCountryDtos();

        String countryDtoName = actualCountryDtos.stream().findFirst().get().getName();
        int countryDtoNaId = actualCountryDtos.stream().findFirst().get().getId();

        Assertions.assertTrue(future.isDone());
        assertEquals(1, actualCountryDtos.size());
        assertEquals(1, countryDtoNaId);
        assertEquals("USA", countryDtoName);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test get futureGenreDtos")
    void whenGetFutureGenreDtos_thenFutureReturned() throws ExecutionException, InterruptedException, TimeoutException {

        Future<EnrichmentTaskResult> future = futureService.getFutureGenreDtos(2, new EnrichmentTaskResult());
        Set<GenreDto> actualGenreDtos = future.get(5, TimeUnit.SECONDS).getGenreDtos();
        String genreDtoName = actualGenreDtos.stream().findFirst().get().getName();
        int genreDtoNaId = actualGenreDtos.stream().findFirst().get().getId();

        Assertions.assertTrue(future.isDone());
        assertEquals(1, actualGenreDtos.size());
        assertEquals(2, genreDtoNaId);
        assertEquals("криминал", genreDtoName);
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test get futureReviewDtos")
    void whenGetFutureReviewDtos_thenFutureReturned() throws ExecutionException, InterruptedException, TimeoutException {

        Future<EnrichmentTaskResult> future = futureService.getFutureReviewDtos(2, new EnrichmentTaskResult());
        Set<ReviewDto> actualReviewDtos = future.get(5, TimeUnit.SECONDS).getReviewDtos();

        Assertions.assertTrue(future.isDone());
        assertEquals(2, actualReviewDtos.size());
    }

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test get future after timeout and throws exception")
    void whenGetFutureReviewDtos_afterTimeoutExecution_thenTimeoutExceptionTrows() {
        Assertions.assertThrows(TimeoutException.class, () -> {

            Future<EnrichmentTaskResult> future = futureService.getFutureReviewDtos(2, new EnrichmentTaskResult());
            Set<ReviewDto> actualReviewDtos = future.get(0, TimeUnit.SECONDS).getReviewDtos();

            Assertions.assertTrue(future.isCancelled());
            assertEquals(0, actualReviewDtos.size());
        });
    }
}