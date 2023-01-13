package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.mapper.GenreMapper;
import com.bondarenko.movieland.repository.GenreRepository;
import com.bondarenko.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> findAll() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toGenreDtos(genres);
    }

    @Override
    public Genre findGenreById(int genreId) {
        return genreRepository.findGenreById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }

    @Override
    public Set<Genre> findByIdIn(List<Integer> genreIds) {
        return genreRepository.findByIdIn(genreIds);
    }

    @Override
    public Set<GenreDto> findByMovieId(int movieId) {
        return genreMapper.toGenreDtos(genreRepository.findByMovieId(movieId));
    }
}
