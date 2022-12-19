package com.bondarenko.movieland.cache;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.repository.DefaultGenreRepository;
import com.bondarenko.movieland.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenreCache implements GenreRepository {
    private final CopyOnWriteArrayList<Genre> genresList = new CopyOnWriteArrayList<>();
    private final DefaultGenreRepository defaultGenreRepository;

    @Override
    public List<Genre> findAll() {
        log.info("Get {} genres from cache", genresList.size());
        return new ArrayList<>(genresList);
    }

    @PostConstruct
    @Scheduled(fixedRate = 4, initialDelay = 4, timeUnit = TimeUnit.HOURS)
    private void enrichCache() {
        List<Genre> genres = defaultGenreRepository.findAll();
        genresList.clear();
        log.info("Enrich genre cache, genres in cache {} ", genresList.size());
        genresList.addAll(genres);
    }
}