package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();

    Optional<Genre> findGenreById(int id);

    Set<Genre> findByIdIn(List<Integer> genreIds);

    @Query(value = "SELECT genre.id, genre.name FROM genre  INNER JOIN movie_genre ON genre.id=movie_genre.genre_id  WHERE movie_genre.movie_id=?;", nativeQuery = true)
    List<Genre> findByMovieId(int id);

}