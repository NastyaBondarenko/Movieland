package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultEnrichmentService implements EnrichmentService {
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;

    @Override
    public MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto, int movieId) {
        Set<CountryDto> countryDtos = countryService.findByMovieId(movieId);
        Set<GenreDto> genreDtos = genreService.findByMovieId(movieId);
        Set<ReviewDto> reviewDtos = reviewService.findByMovieId(movieId);

        movieDetailsDto.setCountries(countryDtos);
        movieDetailsDto.setGenres(genreDtos);
        movieDetailsDto.setReviews(reviewDtos);
        return movieDetailsDto;
    }
}
