package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findMoviesByGenresIn(Set<Genre> genre);
//@Query(value = "SELECT movie.id, movie.name_russian, movie.name_native, movie.year_of_release,movie.description," +
//        " movie.rating,movie.price,movie.picture_path,movie.votes  FROM movie  INNER JOIN movies_genres " +
//        "ON movie.id=movies_genres.movie_id  WHERE movies_genres.genre_id=?", nativeQuery = true)
//List<Movie> findMovieByGenreId(int genreId);

    Movie findMovieById(int id);


//    @EntityGraph(attributePaths = {"genre"})
//    List<Movie> findByGenres_Id(int id);



}