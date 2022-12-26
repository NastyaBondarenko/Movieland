package com.bondarenko.movieland.repository;

import com.bondarenko.movieland.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    @EntityGraph("child.withParent")
//    List<Review> findWithParentById(int id);
//List<Review> findByMovie_Id(int id);
//@EntityGraph(attributePaths = {"user"})
//List<Review> findByMovie_Id(int id);
}
