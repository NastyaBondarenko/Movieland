//package com.bondarenko.movieland.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//
//@Entity
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(name = "movie_genre")
//public class MovieGenre {
////    @Id
////    int id;
//    //ManyToOne
//
//    @EmbeddedId
//    private MovieGenreId id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_id", referencedColumnName = "id")
//    private Movie movie;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "genre_id", referencedColumnName = "id")
//    private Genre genre;
//
//    @NoArgsConstructor
//    @Embeddable
//    public static class MovieGenreId implements Serializable {
//        private int movieId;
//        private int genreId;
//    }
//}
//
//
