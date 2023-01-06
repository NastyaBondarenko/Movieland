package com.bondarenko.movieland.cache;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Cache
@Primary
@RequiredArgsConstructor
public class GenreCache implements GenreRepository {
    private volatile List<Genre> genres;
    private final GenreRepository genreRepository;

    @PostConstruct
    @Transactional(readOnly = true)
    @Scheduled(fixedRateString = "${scheduled.fixedRate}",
            initialDelayString = "${scheduled.initialDelay}", timeUnit = TimeUnit.HOURS)
    public void enrichCache() {
        genres = genreRepository.findAll();
        log.info("Enrich genre cache, genres in cache {} ", genres.size());
    }

    @Override
    public List<Genre> findAll() {
        log.info("Get {} genres from cache", genres.size());
        return new ArrayList<>(genres);
    }

    @Override
    public Optional<Genre> findGenreById(int id) {
        return genreRepository.findById(id);
    }

    @Override
    public Set<Genre> findByIdIn(List<Integer> id) {
        return genreRepository.findByIdIn(id);
    }

    @Override
    public <S extends Genre> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Genre> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Genre> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Genre> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
    }

    @Override
    public void delete(Genre entity) {
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
    }

    @Override
    public void deleteAll(Iterable<? extends Genre> entities) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public void flush() {
    }

    @Override
    public <S extends Genre> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Genre> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Genre> entities) {
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
    }

    @Override
    public void deleteAllInBatch() {
    }

    @Override
    public Genre getOne(Integer integer) {
        return null;
    }

    @Override
    public Genre getById(Integer integer) {
        return null;
    }

    @Override
    public Genre getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Genre> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Genre> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Genre> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Genre> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Genre> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Genre> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Genre, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Genre> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Genre> findAll(Pageable pageable) {
        return null;
    }
}