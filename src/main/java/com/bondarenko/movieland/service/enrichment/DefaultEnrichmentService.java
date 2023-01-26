package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.Review;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
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
    public Movie enrichMovie(Movie movie) {
        int movieId = movie.getId();
        Set<Country> countries = countryService.findByMovieId(movieId);
        Set<Genre> genres = genreService.findByMovieId(movieId);
        Set<Review> reviews = reviewService.findByMovieId(movieId);

        movie.setCountries(countries);
        movie.setGenres(genres);
        movie.setReviews(reviews);
        return movie;
    }

    @Override
    public Movie enrichMovieWithGenresAndCountries(Movie movie, MovieRequestDto movieDto) {
        Set<Genre> genres = genreService.findByIdIn(movieDto.getGenreIds());
        Set<Country> countries = countryService.findByIdIn(movieDto.getCountryIds());
        movie.setGenres(genres);
        movie.setCountries(countries);
        return movie;
    }
}
