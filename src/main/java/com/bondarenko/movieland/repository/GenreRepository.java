package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();

    Optional<Genre> findGenreById(int id);





//    @Query("SELECT DISTINCT person FROM Person person " +
//            "JOIN FETCH person.addresses addresses")
//    List<Genre> findGenresByMovieIn(Set<Movie> movies);

//    List<Genre> findGenresByMovieIn(Set<Movie> movies);

//    @Query(value = "select u from Genre u left join fetch u.movie c where c = :movie")
//    List<Genre> findByMovie (@Param(value = "movie") Movie movie);

//    @Query("select c from Genre c join fetch c.movie where c.id = :id")
//    List<Genre> findByIdFetchGenre(@Param("id") int id);
}