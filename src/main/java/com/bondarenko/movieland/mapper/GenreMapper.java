package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto genreToGenreDto(Genre genre);

    List<GenreDto> genresToGenreDtos(List<Genre> genres);
}
