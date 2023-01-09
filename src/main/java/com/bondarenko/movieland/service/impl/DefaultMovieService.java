package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.MovieDetailsDto;
import com.bondarenko.movieland.dto.MovieDto;
import com.bondarenko.movieland.dto.MovieDtoShort;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.entity.*;
import com.bondarenko.movieland.exceptions.MovieNotFoundException;
import com.bondarenko.movieland.mapper.MovieMapper;
import com.bondarenko.movieland.repository.MovieRepository;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.CurrencyService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.MovieService;
import com.bondarenko.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    private final CurrencyService currencyService;
    private final CountryService countryService;
    private final ReviewService reviewService;
    private final GenreService genreService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        List<Movie> movies = movieRepository.findAll();
        if (movieRequest.getPrice() != null || movieRequest.getRating() != null) {
            List<Movie> resultList = movieRepository.getAll(movieRequest);

            return movieMapper.toMovieDtos(resultList);
        }
        return movieMapper.toMovieDtos(movies);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getRandom() {
        List<Movie> randomMovies = movieRepository.getRandom();
        return movieMapper.toMovieDtos(randomMovies);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDetailsDto getById(int id, CurrencyType currencyType) {
        Movie movie = movieRepository.findMovieById(id).orElseThrow(() -> new MovieNotFoundException(id));
        MovieDetailsDto movieDetailsDto = movieMapper.toMovieDetailsDto(movie);
        Set<ReviewDto> reviewDtos = reviewService.findByMovie(movie);
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
            List<Movie> resultList = movieRepository.getByGenre(movieRequest, genreId);
            return movieMapper.toMovieDtos(resultList);
        }
        return movieMapper.toMovieDtos(getMoviesByGenre(genreId));
    }

    @Override
    public void add(MovieDtoShort movieDtoShort) {
        Set<Genre> genres = getGenresByIds(movieDtoShort);
        Set<Country> countries = getCountriesByIds(movieDtoShort);

        Movie movie = movieMapper.toMovie(movieDtoShort);
        movie.setGenres(genres);
        movie.setCountries(countries);
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void update(MovieDtoShort movieDtoShort, int movieId) {
        Set<Genre> genres = getGenresByIds(movieDtoShort);
        Set<Country> countries = getCountriesByIds(movieDtoShort);

        Movie movie = movieMapper.toMovie(movieDtoShort);
//        Car updateCar = movieRepository.findById(movieId)
//                .map(movie -> movieMapper.update(car, carDto))
//                .orElseThrow(() -> new CarNotFoundException("Car not found"));
//        return carMapper.carToCarDto(carRepository.save(updateCar));


    }

    private List<Movie> getMoviesByGenre(int genreId) {
        Genre genre = genreService.findGenreById(genreId);
        return movieRepository.findMoviesByGenresIn(Set.of(genre));
    }

    private Set<Genre> getGenresByIds(MovieDtoShort movieDtoShort) {
        List<Integer> genresId = movieDtoShort.getGenres();
        return genreService.findByIdIn(genresId);
    }

    private Set<Country> getCountriesByIds(MovieDtoShort movieDtoShort) {
        List<Integer> countryIds = movieDtoShort.getCountries();
        return countryService.findByIdIn(countryIds);
    }
}