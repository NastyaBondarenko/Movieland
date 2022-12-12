package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "SELECT movie.id, movie.name_russian, movie.name_native, movie.year_of_release,movie.description," +
            " movie.rating,movie.price,movie.picture_path,movie.votes  FROM movie  INNER JOIN movie_genre " +
            "ON movie.id=movie_genre.movie_id  WHERE movie_genre.genre_id=?", nativeQuery = true)
    List<Movie> findMovieByGenreId(int genreId);
}