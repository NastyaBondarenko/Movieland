package com.bondarenko.movieland.cache;

import com.bondarenko.movieland.dao.GenreDao;
import com.bondarenko.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenreCache {
    private final CopyOnWriteArrayList<Genre> genresList = new CopyOnWriteArrayList<>();
    private final GenreDao genreDao;

    @PostConstruct
    @Scheduled(fixedRate = 4, initialDelay = 4, timeUnit = TimeUnit.HOURS)
    public void enrichCache() {
        List<Genre> genres = genreDao.findAll();
        genresList.addAll(genres);
        log.info("Enrich genre cache, total genres in cache {} ", genresList.size());
    }

    public List<Genre> getCachedGenre() {
        log.info("Load {} genres from cache", genresList.size());
        return genresList;
    }
}