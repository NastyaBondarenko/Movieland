package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.CompletableFutureService;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Slf4j
@AllArgsConstructor
@Service
public class DefaultCompletableFutureService implements CompletableFutureService {
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    private ExecutorService executorService;

    @Override
    public List<CompletableFuture<MovieDetailsDto>> getCompletableFuturesList(int id, MovieDetailsDto movieDetailsDto) {
        CompletableFuture<MovieDetailsDto> futureEnrichedByCountryDtos = enrichByCountryDtos(id, movieDetailsDto);
        CompletableFuture<MovieDetailsDto> futureEnrichedByReviewDtos = enrichByReviewDtos(id, movieDetailsDto);
        CompletableFuture<MovieDetailsDto> futureEnrichedByGenreDtos = enrichByGenreDtos(id, movieDetailsDto);
        return List.of(futureEnrichedByCountryDtos, futureEnrichedByReviewDtos, futureEnrichedByGenreDtos);
    }

    @VisibleForTesting
    CompletableFuture<MovieDetailsDto> enrichByCountryDtos(int movieId, MovieDetailsDto movieDetailsDto) {
        Set<CountryDto> countryDto = countryService.findByMovieId(movieId);
        movieDetailsDto.setCountries(countryDto);
        return CompletableFuture.supplyAsync(() -> movieDetailsDto, executorService);
    }

    @VisibleForTesting
    CompletableFuture<MovieDetailsDto> enrichByGenreDtos(int movieId, MovieDetailsDto movieDetailsDto) {
        Set<GenreDto> genreDtos = genreService.findByMovieId(movieId);
        movieDetailsDto.setGenres(genreDtos);
        return CompletableFuture.supplyAsync(() -> movieDetailsDto, executorService);
    }

    @VisibleForTesting
    CompletableFuture<MovieDetailsDto> enrichByReviewDtos(int movieId, MovieDetailsDto movieDetailsDto) {
        Set<ReviewDto> reviewDtos = reviewService.findByMovieId(movieId);
        movieDetailsDto.setReviews(reviewDtos);
        return CompletableFuture.supplyAsync(() -> movieDetailsDto, executorService);
    }
}