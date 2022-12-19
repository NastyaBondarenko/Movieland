package com.bondarenko.movieland.cache;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.repository.DefaultGenreRepository;
import com.bondarenko.movieland.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenreCache implements GenreRepository {
    private volatile List<Genre> genres;
    private final DefaultGenreRepository defaultGenreRepository;

    @PostConstruct
    @Scheduled(fixedRate = 4, initialDelay = 4, timeUnit = TimeUnit.HOURS)
    @Transactional(readOnly = true)
    public void enrichCache() {
        log.info("Enrich genre cache, genres in cache {} ", genres.size());
        genres = defaultGenreRepository.findAll();
    }

    @Override
    public List<Genre> findAll() {
        log.info("Get {} genres from cache", genres.size());
        return new ArrayList<>(genres);
    }
}