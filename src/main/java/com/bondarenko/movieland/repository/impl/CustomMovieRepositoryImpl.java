package com.bondarenko.movieland.repository.impl;

import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.service.entity.request.MovieRequest;
import com.bondarenko.movieland.service.entity.common.SortDirection;
import com.bondarenko.movieland.repository.CustomMovieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class CustomMovieRepositoryImpl implements CustomMovieRepository {
    @Value("${movies.random.count:3}")
    private int randomMovieCount;
    private static final String RATING_PARAMETER = "rating";
    private static final String PRICE_PARAMETER = "price";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> findAll(MovieRequest movieRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        Order order = getOrder(movieRequest, builder, root);
        query.orderBy(order);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Movie> findByGenre(MovieRequest movieRequest, Integer genreId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        Join<Movie, Genre> genre = root.join("genres");

        Order order = getOrder(movieRequest, builder, root);
        query.where(builder.equal(genre.get("id"), genreId)).orderBy(order);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Movie> findRandom() {
        TypedQuery<Movie> query = entityManager
                .createQuery("SELECT m from Movie m ORDER BY random()", Movie.class);
        query.setMaxResults(randomMovieCount);
        return query.getResultList();
    }

    private Order getOrder(MovieRequest movieRequest, CriteriaBuilder builder, Root<Movie> root) {
        SortDirection priceDirection = movieRequest.getPrice();
        SortDirection sortDirection = priceDirection == null ? movieRequest.getRating() : priceDirection;
        String sortColumn = priceDirection == null ? RATING_PARAMETER : PRICE_PARAMETER;

        Path<Object> sortPath = root.get(sortColumn);
        return sortDirection.equals(SortDirection.ASC) ? builder.asc(sortPath) : builder.desc(sortPath);
    }
}
