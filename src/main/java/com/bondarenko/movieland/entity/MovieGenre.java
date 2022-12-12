package com.bondarenko.movieland.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_genre")
public class MovieGenre {
    @Id
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "genre_id")
    private int genreId;
}
