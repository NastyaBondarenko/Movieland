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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;




//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_generator")
//    @SequenceGenerator(name = "review_seq_generator", sequenceName = "review_id_sequence")
//    @Column(name = "id", nullable = false)
//    private Integer id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_id", nullable = false)
//    private Movie movie;
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "user_id", nullable = false)
////    private User user;
//
//    @Column(name = "description", nullable = false, length = 500)
//    private String description;
}