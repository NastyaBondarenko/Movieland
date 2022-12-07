package com.bondarenko.movieland.cache;

import com.bondarenko.movieland.dao.GenreDao;
import com.bondarenko.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class GenreCache {

    private final CopyOnWriteArrayList<Genre> genresList = new CopyOnWriteArrayList<>();
    private final GenreDao genreDao;

    @PostConstruct
    @Scheduled(fixedRate = 4, initialDelay = 4, timeUnit = TimeUnit.SECONDS)
    public void enrichCache() {
        List<Genre> genres = genreDao.findAll();
        genresList.addAll(genres);
    }

    public List<Genre> getCachedGenre() {
        return genresList;
    }
}