package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.AbstractBaseITest;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.dto.request.ReviewRequestDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
public class DefaultReviewServiceITest extends AbstractBaseITest {
    @Autowired
    private DefaultReviewService reviewService;

    @Test
    @DataSet(value = "datasets/review/dataset_reviews.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @ExpectedDataSet("datasets/review/dataset_add_review.yml")
    @DisplayName("when Add ReviewRequestDto then Added ReviewDto Return")
    public void whenAddReviewRequestDto_thenAddedReviewDtoReturn() {
        ReviewRequestDto reviewDto = ReviewRequestDto.builder()
                .movieId(1)
                .description("Гениальное кино!")
                .build();

        ReviewDto actualReviewDto = reviewService.add(reviewDto);

        assertEquals(1, actualReviewDto.getId());
        assertEquals("Гениальное кино!", actualReviewDto.getDescription());
    }
}