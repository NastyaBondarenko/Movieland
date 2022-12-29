package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.entity.CurrencyType;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.MovieRequest;
import com.bondarenko.movieland.entity.SortDirection;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.exceptions.MovieNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.mapper.ReviewMapper;
import com.bondarenko.movieland.repository.GenreRepository;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.repository.ReviewRepository;
import com.bondarenko.movieland.service.CurrencyService;
import com.bondarenko.movieland.service.MovieService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    @Value("${movies.random.count:3}")
    private int randomMovieCount;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CurrencyService currencyService;
    private final ReviewMapper reviewMapper;
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
    @Transactional(readOnly = true)
    public MovieDetailsDto getById(int id, CurrencyType currencyType) {
        Movie movie = movieRepository.findMovieById(id).orElseThrow(() -> new MovieNotFoundException(id));
        MovieDetailsDto movieDetailsDto = movieMapper.toMovieDetailsDto(movie);
        Set<ReviewDto> reviewDtos = reviewMapper.toReviewDtos(reviewRepository.findByMovie(movie));
        movieDetailsDto.setReviews(reviewDtos);

        if (currencyType != null) {
            double convertedPrice = currencyService.convertPrice(movieDetailsDto.getPrice(), currencyType);
            movieDetailsDto.setPrice(convertedPrice);
        }
        return movieDetailsDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getByGenre(MovieRequest movieRequest) {
        Integer genreId = movieRequest.getGenreId();

        if (movieRequest.getPrice() != null || movieRequest.getRating() != null) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
            Root<Movie> root = query.from(Movie.class);
            Join<Movie, Genre> genre = root.join("genres");

            Order order = getOrder(movieRequest, builder, root);
            query.where(builder.equal(genre.get("id"), genreId)).orderBy(order);
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
        return movieRepository.findMoviesByGenresIn(Set.of(genre));
    }
}