package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.Review;
import com.bondarenko.movieland.exceptions.CountryNotFoundException;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.service.entity.common.EnrichmentResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ParallelEnrichmentService implements EnrichmentService {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final CountryService countryService;
    private final GenreService genreService;
    private final ReviewService reviewService;
    @Value("${task.timeout.seconds:5}")
    private int taskTimeout;

    @Override
    public Movie enrichMovie(Movie movie) {
        int movieId = movie.getId();
        EnrichmentResult enrichmentResult = new EnrichmentResult();

        EnrichByGenresCallable enrichByGenresCallable = new EnrichByGenresCallable(enrichmentResult, genreService, movieId);
        EnrichByCountriesCallable enrichByCountriesCallable = new EnrichByCountriesCallable(enrichmentResult, countryService, movieId);
        EnrichByReviewsCallable enrichByReviewsCallable = new EnrichByReviewsCallable(enrichmentResult, reviewService, movieId);

        List<Callable<EnrichmentResult>> tasks = List.of(enrichByGenresCallable, enrichByCountriesCallable, enrichByReviewsCallable);

        List<Future<EnrichmentResult>> taskResults;
        try {
            taskResults = executorService.invokeAll(tasks, taskTimeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<EnrichmentResult> enrichmentResults = getEnrichmentResults(taskResults);
        enrichByTaskResults(enrichmentResults, movie);
        return movie;
    }

    private List<EnrichmentResult> getEnrichmentResults(List<Future<EnrichmentResult>> taskResults) {
        return taskResults.stream().map(future -> {
            try {
                return future.get(taskTimeout, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    private void enrichByTaskResults(List<EnrichmentResult> enrichmentResults, Movie movie) {
        Set<Review> reviews = enrichmentResults.stream()
                .map(EnrichmentResult::getReviews)
                .filter(Objects::nonNull).findFirst().orElse(null);

        Set<Genre> genres = enrichmentResults.stream()
                .map(EnrichmentResult::getGenres)
                .filter(Objects::nonNull).findFirst().orElseThrow(GenreNotFoundException::new);

        Set<Country> countries = enrichmentResults.stream()
                .map(EnrichmentResult::getCountries)
                .filter(Objects::nonNull).findFirst().orElseThrow(CountryNotFoundException::new);

        movie.setCountries(countries);
        movie.setGenres(genres);
        movie.setReviews(reviews);
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