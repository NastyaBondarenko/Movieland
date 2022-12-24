package com.bondarenko.movieland.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_sequence")
    @SequenceGenerator(name = "review_id_sequence", sequenceName = "review_id_sequence")
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Movie movie;

//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private User user;






}