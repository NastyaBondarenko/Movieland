package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.entity.Review;
import com.bondarenko.movieland.service.dto.request.ReviewDtoShot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper
public interface ReviewMapper {
    @Mapping(target = "movie", ignore = true)
    Set<ReviewDto> toReviewDtos(Set<Review> reviews);

    @Mapping(target = "movie.id", source = "movieId")
    Review toReview(ReviewDtoShot reviewDtoShot);
}