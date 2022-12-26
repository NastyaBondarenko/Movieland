package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.*;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.CountryRepository;
import com.bondarenko.movieland.repository.GenreRepository;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.repository.ReviewRepository;
import com.bondarenko.movieland.service.MovieService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    @Value("${movies.random.count:3}")
    private int randomMovieCount;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final ReviewRepository reviewRepository;
    private final MovieMapper movieMapper;
    private static final String RATING_PARAMETER = "rating";
    private static final String PRICE_PARAMETER = "price";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        List<Movie> movies = movieRepository.findAll();
        if (movieRequest.getPrice() != null || movieRequest.getRating() != null) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
            Root<Movie> root = query.from(Movie.class);
            Order order = getOrder(movieRequest, builder, root);
            query.orderBy(order);
            return movieMapper.toMovieDtos(entityManager.createQuery(query).getResultList());
        }
        return movieMapper.toMovieDtos(movies);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getRandom() {
        Pageable pageable = PageRequest.of(3, 6);
        List<Movie> randomMovies = new ArrayList<>(movieRepository.findAll(pageable).toList());
        Collections.shuffle(randomMovies);
        return movieMapper.toMovieDtos(randomMovies.subList(0, randomMovieCount));
    }

    @Override
    public Movie getById(int id) {
        Movie movie = movieRepository.findMovieById(id);
//        List<Review> reviews = reviewRepository.findByMovie_Id(id);
//        movie.setReviews(new HashSet<>(reviews));
//        Set<Country> countries = countryRepository.findByMovies_Id_MovieId(id);
////        List<Review> reviews = reviewRepository.findWithParentById(id);
//        movie.setCountries(countries);
//        List<Review> byMovie_id = reviewRepository.findByMovie_Id(id);
//        List<Country> ddd = countryRepository.findByMovie_Id(id);
//        movie.setReviews(new HashSet<>(byMovie_id));
//        movie.setCountries(new HashSet<>(ddd));
        return movie;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getByGenre(MovieRequest movieRequest) {
        Integer genreId = movieRequest.getGenreId();

        if (movieRequest.getPrice() != null || movieRequest.getRating() != null) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
            Root<Movie> root = query.from(Movie.class);

            Join<Movie, Genre> genre = root.join("genre");
            query.where(builder.equal(genre.get("id"), genreId));
            Order order = getOrder(movieRequest, builder, root);
            query.orderBy(order);

            entityManager.createQuery(query).getResultList();
            return movieMapper.toMovieDtos(entityManager.createQuery(query).getResultList());
        }
        return movieMapper.toMovieDtos(getMoviesByGenre(genreId));
    }

    private Order getOrder(MovieRequest movieRequest, CriteriaBuilder builder, Root<Movie> root) {
        SortDirection priceDirection = movieRequest.getPrice();
        SortDirection sortDirection = priceDirection == null ? movieRequest.getRating() : priceDirection;
        String sortColumn = priceDirection == null ? RATING_PARAMETER : PRICE_PARAMETER;

        Path<Object> sortPath = root.get(sortColumn);
        return sortDirection.equals(SortDirection.ASC) ? builder.asc(sortPath) : builder.desc(sortPath);
    }

    private List<Movie> getMoviesByGenre(int genreId) {
        Genre genre = genreRepository.findGenreById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
//        return movieRepository.findMoviesByGenreIn(Set.of(genre));
        return  null;
    }
}