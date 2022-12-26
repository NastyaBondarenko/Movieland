package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Country;
import com.bondarenko.movieland.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
//    @Query("select c from Country c join fetch c.movie where c.id = :id")
//    List<Country> findByIdFetchMovie(int id);
//    @EntityGraph("child.withParent")
//    List<Country> findWithParentById(int id);
//List<Country> findAllByMovieId(int id);

//    Set<Country> findByMovies_Id_MovieId(int movieId);

//    List<Country> findByMovie_Id(int id);

}
