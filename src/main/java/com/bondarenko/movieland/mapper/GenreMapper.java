package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {

    List<GenreDto> genresToGenreDtos(List<Genre> genres);

    GenreDto genreToGenreDto(Genre genre);
}
