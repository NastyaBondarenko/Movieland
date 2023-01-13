package com.bondarenko.movieland.mapper;

import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface GenreMapper {
    List<GenreDto> toGenreDtos(List<Genre> genres);
    Set<GenreDto> toGenreDtos(Set<Genre> genres);
    GenreDto toGenreDto(Genre genre);
}
