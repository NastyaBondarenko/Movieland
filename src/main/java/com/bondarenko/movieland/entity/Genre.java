package com.bondarenko.movieland.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Getter
@Entity
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

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    //
//    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<MovieGenre> movies = new ArrayList<>();
    //    @JoinColumn(name = "movie_id", referencedColumnName = "id")
//    @ManyToOne
//    //    @JoinColumn(name = "movie_id", referencedColumnName = "id")
//
//    private Movie movie;
}