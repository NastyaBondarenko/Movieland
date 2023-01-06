package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @EntityGraph(attributePaths = {"user"})
    Set<Review> findByMovie(Movie movie);

    Optional<Review> findByMovieAndDescription(Movie movie, String description);
}
