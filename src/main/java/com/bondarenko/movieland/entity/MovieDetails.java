package com.bondarenko.movieland.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movie_details")
public class MovieDetails {
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @OneToMany(mappedBy = "movieDetails", cascade = CascadeType.ALL)
    private List<Country> countries = new ArrayList<>();

    @OneToMany(mappedBy = "movieDetails", cascade = CascadeType.ALL)
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movieDetails", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}