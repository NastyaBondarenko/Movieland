package com.bondarenko.movieland.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;


@Getter
@Entity
@Setter
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genres_id_sequence")
    @SequenceGenerator(name = "genres_id_sequence", sequenceName = "genres_id_sequence")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "movie_details_id", referencedColumnName = "id")
//    private MovieDetails movieDetails;

    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Movie movie;
}