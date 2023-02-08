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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

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

        Future<Set<Country>> futureCountries = findFutureCountries(movie);
        Future<Set<Review>> futureReviews = findFutureReviews(movie);
        Future<Set<Genre>> futureGenres = findFutureGenres(movie);

        enrichWithGenres(movie, futureGenres);
        enrichWithCountries(movie, futureCountries);
        enrichWithReviews(movie, futureReviews);

        return movie;
    }

    private Future<Set<Genre>> findFutureGenres(Movie movie) {
        Supplier<Set<Genre>> genreTask = () -> {
            log.info("Enrich movie with reviews in {}", Thread.currentThread().getName());
            return genreService.findByMovieId(movie.getId());
        };
        return CompletableFuture.supplyAsync(genreTask, executorService);
    }

    private Future<Set<Country>> findFutureCountries(Movie movie) {
        Supplier<Set<Country>> reviewTask = () -> {
            log.info("Enrich movie with countries in {}", Thread.currentThread().getName());
            return countryService.findByMovieId(movie.getId());
        };
        return CompletableFuture.supplyAsync(reviewTask, executorService);
    }

    private Future<Set<Review>> findFutureReviews(Movie movie) {
        Supplier<Set<Review>> reviewTask = () -> {
            log.info("Enrich movie with reviews in {}", Thread.currentThread().getName());
            return reviewService.findByMovieId(movie.getId());
        };
        return CompletableFuture.supplyAsync(reviewTask, executorService);
    }

    private void enrichWithGenres(Movie movie, Future<Set<Genre>> futureGenres) {
        try {
            Set<Genre> genres = futureGenres.get(taskTimeout, TimeUnit.SECONDS);
            movie.setGenres(genres);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            futureGenres.cancel(true);
            log.info("Enrichment movie with genres is cancelled= {}", futureGenres.isCancelled());
        }
    }

    private void enrichWithCountries(Movie movie, Future<Set<Country>> futureCountries) {
        try {
            Set<Country> countries = futureCountries.get(taskTimeout, TimeUnit.SECONDS);
            movie.setCountries(countries);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            futureCountries.cancel(true);
            log.info("Enrichment movie with genres is cancelled={}", futureCountries.isCancelled());
        }
    }

    private void enrichWithReviews(Movie movie, Future<Set<Review>> futureReviews) {
        try {
            Set<Review> reviews = futureReviews.get(taskTimeout, TimeUnit.SECONDS);
            movie.setReviews(reviews);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            futureReviews.cancel(true);
            log.info("Enrichment movie with reviews is cancelled={}", futureReviews.isCancelled());
        }
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