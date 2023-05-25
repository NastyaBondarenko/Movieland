
package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.Review;
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

        Movie movie = Movie.builder()
                .id(2)
                .nameNative("The Shawshank Redemption")
                .nameRussian("Побег из Шоушенка")
                .price(100)
                .description("От лица главного героя Форреста Гампа")
                .genres(null)
                .reviews(null)
                .countries(null)
                .build();
        enrichmentService.enrichMovie(movie);

        Set<Genre> actualGenres = movie.getGenres();
        Set<Review> actualReviews = movie.getReviews();
        Set<Country> actualCountries = movie.getCountries();

        Assertions.assertEquals(2, actualReviews.size());
        Assertions.assertEquals(1, actualGenres.size());
        Assertions.assertEquals(1, actualCountries.size());
        Assertions.assertEquals(2, movie.getId());
        Assertions.assertEquals("The Shawshank Redemption", movie.getNameNative());
        Assertions.assertEquals("Побег из Шоушенка", movie.getNameRussian());
        Assertions.assertEquals("От лица главного героя Форреста Гампа", movie.getDescription());
    }
}