package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.entity.Genre;
import com.bondarenko.movieland.entity.Movie;
import com.bondarenko.movieland.entity.MovieRequest;
import com.bondarenko.movieland.entity.SortDirection;
import com.bondarenko.movieland.exceptions.GenreNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.CountryRepository;
import com.bondarenko.movieland.repository.GenreRepository;
import com.bondarenko.movieland.repository.MovieRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    @Value("${movies.random.count:3}")
    private int randomMovieCount;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
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
//        List<Country> countries = countryRepository.findCountriesByMovieIsIn(movie);


//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
//        Root<Movie> root = query.from(Movie.class);

//        Join<Movie, Country> country = root.join("country");
//        query.where(builder.equal(country.get("id"), id));
//        entityManager.createQuery(query).getResultList();

//        Join<Movie, Review> review = root.join("review");
//        query.where(builder.equal(review.get("id"), id));
//        entityManager.createQuery(query).getResultList();

//        Movie resultList = entityManager.createQuery(query).getResultList().stream().findFirst().get();
//        List<Country> country1 = resultList.getCountry();
//        movie.setCountry(country1);
//        int movieId = movie.getId();
//        List<Genre> genres = genreRepository.findByMovieId(movieId);


//        MovieDetails movieDetails = new MovieDetails();
//        movieDetails.setMovie(movie);
//        movieDetails.setGenres(genres);


//        Country country =new Country();
//        country.setCountryId();

//        movie.setCountries(countries);

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
        return movieRepository.findMoviesByGenreIn(Set.of(genre));
    }
}
