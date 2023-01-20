
package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Set;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class ParallelEnrichmentServiceITest extends AbstractBaseITest {
    @Autowired
    private ParallelEnrichmentService enrichmentService;

    @Test
    @DataSet(value = "datasets/movie/dataset_movies.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("test enrich MovieDetailsDto")
    void whenEnrichMovieDetailsDto_thenMovieDetailsDtoEnrichedByAppropriateDtos() {

        MovieDetailsDto movieDetailsDto = MovieDetailsDto.builder()
                .id(2)
                .nameNative("The Shawshank Redemption")
                .nameRussian("Побег из Шоушенка")
                .price(100)
                .description("От лица главного героя Форреста Гампа")
                .genres(null)
                .reviews(null)
                .countries(null)
                .build();
        enrichmentService.enrichMovieDetailsDto(movieDetailsDto);

        Set<GenreDto> actualGenreDtos = movieDetailsDto.getGenres();
        Set<ReviewDto> actualReviewDtos = movieDetailsDto.getReviews();
        Set<CountryDto> actualCountryDtos = movieDetailsDto.getCountries();

        Assertions.assertEquals(2, actualReviewDtos.size());
        Assertions.assertEquals(1, actualGenreDtos.size());
        Assertions.assertEquals(1, actualCountryDtos.size());
        Assertions.assertEquals(2, movieDetailsDto.getId());
        Assertions.assertEquals("The Shawshank Redemption", movieDetailsDto.getNameNative());
        Assertions.assertEquals("Побег из Шоушенка", movieDetailsDto.getNameRussian());
        Assertions.assertEquals("От лица главного героя Форреста Гампа", movieDetailsDto.getDescription());
    }
}