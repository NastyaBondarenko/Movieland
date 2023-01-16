package com.bondarenko.movieland.service.impl.callable;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Slf4j
@AllArgsConstructor
@Service
public class CompletableFutures {
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;

    public CompletableFuture<MovieDetailsDto> getCountryDtos(int movieId, MovieDetailsDto movieDetailsDto) {
        Set<CountryDto> countryDto = countryService.findByMovieId(movieId);
        movieDetailsDto.setCountries(countryDto);
        return CompletableFuture.supplyAsync(() -> movieDetailsDto);
    }

    public CompletableFuture<MovieDetailsDto> getGenreDtos(int movieId, MovieDetailsDto movieDetailsDto) {
        Set<GenreDto> genreDtos = genreService.findByMovieId(movieId);
        movieDetailsDto.setGenres(genreDtos);
        return CompletableFuture.supplyAsync(() -> movieDetailsDto);
    }

    public CompletableFuture<MovieDetailsDto> getReviewDtos(int movieId, MovieDetailsDto movieDetailsDto) {
        Set<ReviewDto> reviewDtos = reviewService.findByMovieId(movieId);
        movieDetailsDto.setReviews(reviewDtos);
        return CompletableFuture.supplyAsync(() -> movieDetailsDto);
    }
}