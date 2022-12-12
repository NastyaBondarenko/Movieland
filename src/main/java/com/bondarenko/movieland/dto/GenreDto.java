package com.bondarenko.movieland.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class GenreDto {
    private int genreId;
    private String name;
}
