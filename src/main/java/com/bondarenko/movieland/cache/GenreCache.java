package com.bondarenko.movieland.cache;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenreCache {
    private volatile List<Genre> genres;
    private final GenreRepository genreRepository;

    @PostConstruct
    @Scheduled(fixedRate = 4, initialDelay = 4, timeUnit = TimeUnit.HOURS)
    @Transactional(readOnly = true)
    public void enrichCache() {
        genres = genreRepository.findAll();
        log.info("Enrich genre cache, total genres in cache {} ", genres.size());
    }

    public List<Genre> getCachedGenre() {
        log.info("Load {} genres from cache", genres.size());
        return genres;
    }
}