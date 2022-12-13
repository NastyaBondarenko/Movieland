package com.bondarenko.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie_genre")
public class MovieGenre {
    @Id
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "genre_id")
    private int genreId;
}
