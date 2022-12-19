package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultGenreRepository extends JpaRepository<Genre, Integer> {
}