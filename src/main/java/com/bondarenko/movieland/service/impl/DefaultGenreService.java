package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.cache.GenreCache;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.mapper.GenreMapper;
import com.bondarenko.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreCache genreCache;
    private final GenreMapper genreMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        List<Genre> genres = genreCache.getCachedGenre();
        genres.stream().findAny().orElseThrow(GenreNotFoundException::new);
        return genreMapper.genresToGenreDtos(genres);
    }
}
