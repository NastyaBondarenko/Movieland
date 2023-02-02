package com.bondarenko.movieland.cache;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.exceptions.MovieNotFoundException;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.dto.request.MovieRequestDto;
import com.bondarenko.movieland.service.entity.request.MovieRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Slf4j
@Cache
@Primary
@RequiredArgsConstructor
public class EnrichedMovieCache implements MovieRepository {
    private final EnrichmentService enrichmentService;
    private final MovieRepository movieRepository;
    private final Map<Integer, SoftReference<Movie>> cachedMovieMap = new ConcurrentHashMap<>();

    @Override
    public Movie findEnrichedMovieById(int movieId) {
        SoftReference<Movie> movieSoftReference = cachedMovieMap.computeIfAbsent(movieId,
                k -> new SoftReference<>(enrichMovie(movieId)));
        log.info("Get enriched movie with genres, countries, reviews by id={} ", movieId);
        return movieSoftReference.get();
    }

    @Override
    public Movie findEnrichedMovieByCountriesAndGenres(Movie movie, MovieRequestDto movieRequestDto) {
        int movieId = movie.getId();
        SoftReference<Movie> movieSoftReference = cachedMovieMap.computeIfPresent(movieId,
                (k, v) -> new SoftReference<>(enrichMovieByGenresAndCountries(movie, movieRequestDto)));
        log.info("Get enriched movie with genres and countries by id={} ", movieId);
        return movieSoftReference.get();
    }

    @Override
    public List<Movie> findAll(MovieRequest movieRequest) {
        return movieRepository.findAll(movieRequest);
    }

    @Override
    public List<Movie> findByGenre(MovieRequest movieRequest, Integer genreId) {
        return movieRepository.findByGenre(movieRequest, genreId);
    }

    @Override
    public List<Movie> findRandom() {
        return movieRepository.findRandom();
    }

    @Override
    public List<Movie> findMoviesByGenresIn(Set<Genre> genre) {
        return movieRepository.findMoviesByGenresIn(genre);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Movie> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Movie> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Movie> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Movie getOne(Integer integer) {
        return null;
    }

    @Override
    public Movie getById(Integer integer) {
        return movieRepository.getById(integer);
    }

    @Override
    public Movie getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Movie> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Movie> List<S> findAll(Example<S> example) {
        return movieRepository.findAll(example);
    }

    @Override
    public <S extends Movie> List<S> findAll(Example<S> example, Sort sort) {
        return movieRepository.findAll(example, sort);
    }

    @Override
    public <S extends Movie> Page<S> findAll(Example<S> example, Pageable pageable) {
        return movieRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Movie> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Movie> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Movie, R> R
    findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return movieRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Movie> S save(S entity) {
        return movieRepository.save(entity);
    }

    @Override
    public <S extends Movie> List<S> saveAll(Iterable<S> entities) {
        return movieRepository.saveAll(entities);
    }

    @Override
    public Optional<Movie> findById(Integer integer) {
        return movieRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return movieRepository.existsById(integer);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findAllById(Iterable<Integer> integers) {
        return movieRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Movie entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Movie> findAll(Sort sort) {
        return movieRepository.findAll(sort);
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    private Movie enrichMovieByGenresAndCountries(Movie movie, MovieRequestDto movieRequestDto) {
        return enrichmentService.enrichMovieWithGenresAndCountries(movie, movieRequestDto);
    }

    private Movie enrichMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        return enrichmentService.enrichMovie(movie);
    }
}