package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.dto.request.ReviewDtoShot;
import com.bondarenko.movieland.entity.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewMapperTest {
    private final ReviewMapper reviewMapper = Mappers.getMapper(ReviewMapper.class);

    @Test
    @DisplayName("Mapping Review list to ReviewDto list")
    void givenReviews_whenMapReviewsToReviewDtos_thenReviewsDtosReturn() {
        Review reviewFirst = Review.builder()
                .id(1)
                .description("Гениальное кино!")
                .build();

        Review reviewSecond = Review.builder()
                .id(2)
                .description("Очень хороший фильм")
                .build();

        Review reviewThird = Review.builder()
                .id(3)
                .description("Рекомендую смотреть всем")
                .build();

        Set<ReviewDto> reviewDtos = reviewMapper.toReviewDtos(Set.of(reviewFirst, reviewSecond, reviewThird));

        ReviewDto reviewDtoFirst = reviewDtos.stream().findFirst().get();

        assertEquals(3, reviewDtos.size());
        assertEquals(1, reviewDtoFirst.getId());
        assertEquals("Гениальное кино!", reviewDtoFirst.getDescription());
    }

    @Test
    @DisplayName("Mapping ReviewDto  to Review")
    void givenReviewDto_whenMapReviewDtoToReview_thenReviewReturn() {
        ReviewDtoShot reviewDto = ReviewDtoShot.builder()
                .movieId(1)
                .description("Гениальное кино!")
                .build();
        Review review = reviewMapper.toReview(reviewDto);

        assertEquals(1, review.getMovie().getId());
        assertEquals("Гениальное кино!", review.getDescription());
    }
}