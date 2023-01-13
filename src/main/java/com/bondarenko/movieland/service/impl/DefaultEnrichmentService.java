package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.CountryDto;
import com.bondarenko.movieland.dto.GenreDto;
import com.bondarenko.movieland.dto.ReviewDto;
import com.bondarenko.movieland.service.CountryService;
import com.bondarenko.movieland.service.GenreService;
import com.bondarenko.movieland.service.ReviewService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.*;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultEnrichmentService {
    //value
    private final long TASK_TIME = 5;
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;
    private ExecutorService executorService;

    MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto, int id) {

        ReviewCallable reviewCallable = new ReviewCallable(reviewService, id);
        GenreCallable genreCallable = new GenreCallable(genreService, id);
        CountryCallable countryCallable = new CountryCallable(countryService, id);

        Future<Set<ReviewDto>> future1 = executorService.submit(reviewCallable);
        Future<Set<CountryDto>> future2 = executorService.submit(countryCallable);
        Future<Set<GenreDto>> future3 = executorService.submit(genreCallable);
        Set<ReviewDto> reviewDtos = null;
        try {
            reviewDtos = future1.get(TASK_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        movieDetailsDto.setReviews(reviewDtos);
        Set<CountryDto> countryDtos = null;
        try {
            countryDtos = future2.get(TASK_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        movieDetailsDto.setCountries(countryDtos);
        Set<GenreDto> genreDtos = null;
        try {
            genreDtos = future3.get(TASK_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        movieDetailsDto.setGenres(genreDtos);
//___

//in for: submit, list.add // 2 for : get //shutdown

//        List<Future<?>> list = new ArrayList<>();
//        list.add(future1);
//        list.add(future2);
//        list.add(future3);
//
//        for (Future<?> future : list) {
//            try {
//
//
//        }
//        for (Future future : list) {
//
//            Object o = future.get();
//
//        } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        }

//        try {

//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        executorService.shutdown();
        return movieDetailsDto;


    }

//    Set<Genre> enrichGenres(GenreCallable genreCallable)  {
//        Future<Set<GenreDto>> future =  executorService.submit(genreCallable);
//        try {
//            return future.get();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
////        executorService.shutdown();
//
//    }
}
