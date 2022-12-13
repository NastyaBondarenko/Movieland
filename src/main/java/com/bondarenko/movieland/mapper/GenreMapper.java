package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreMapper {

    public List<GenreDto> genresToGenreDtos(List<Genre> genres) {
        return genres.stream()
                .map(this::genreToGenreDto)
                .toList();
    }

    public GenreDto genreToGenreDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setGenreId(genre.getGenreId());
        genreDto.setName(genre.getName());
        return genreDto;
    }
}
