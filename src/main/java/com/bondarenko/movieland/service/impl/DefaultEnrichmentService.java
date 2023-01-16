//package com.bondarenko.movieland.service.impl;
//
//import com.bondarenko.movieland.service.CountryService;
//import com.bondarenko.movieland.service.GenreService;
//import com.bondarenko.movieland.service.ReviewService;
//import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
//import com.bondarenko.movieland.service.impl.callable.CountryCallable;
//import com.bondarenko.movieland.service.impl.callable.GenreCallable;
//import com.bondarenko.movieland.service.impl.callable.ReviewCallable;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.*;
//
//@Slf4j
//@Service
//@AllArgsConstructor
//public class DefaultEnrichmentService {
//    //value
//    private final long TASK_TIME = 5;
//    private GenreService genreService;
//    private CountryService countryService;
//    private ReviewService reviewService;
//    private ExecutorService executorService;
//
//    MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto, int id) {
//
////        ReviewCallable reviewCallable = new ReviewCallable(reviewService, id);
////        GenreCallable genreCallable = new GenreCallable(genreService, id);
////        CountryCallable countryCallable = new CountryCallable(countryService, id);
//
//        List<Callable<MovieDetailsDto>> callables = new ArrayList<>();
////        List<Callable<?>> futureList = new ArrayList<Callable<?>>();
////
////        Future<MovieDetailsDto> future1 = executorService.submit(reviewCallable);
////        Future<MovieDetailsDto> future2 = executorService.submit(countryCallable);
////        Future<MovieDetailsDto> future3 = executorService.submit(genreCallable);
//
//
////        CompletableFuture<Void> combinedFuture
////               = CompletableFuture.allOf(future1, future2, future3);
//
//
//        //(2)
//        ReviewCallable reviewCallable = new ReviewCallable(reviewService, id, movieDetailsDto);
//        GenreCallable genreCallable = new GenreCallable(genreService, id, movieDetailsDto);
//        CountryCallable countryCallable = new CountryCallable(countryService, id, movieDetailsDto);
//
////        Thread t=new Thread(futureTask);
////        System.out.println(executorService);
////        System.out.println(future3.isDone()); //False
////        System.out.println(executorService);
//
//        callables.add(reviewCallable);
//        callables.add(genreCallable);
//        callables.add(countryCallable);
//
////        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
//
//
//
//
////        List<Future<?>> futureList = new ArrayList<>();
//
//        List<Future<MovieDetailsDto>> futures = null;
//        try {
//            futures = executorService.invokeAll(callables);
//            System.out.println(executorService);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (Future<MovieDetailsDto> future : futures) {
//            try {
//                processResult(movieDetailsDto, future);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
////        Set<ReviewDto> reviewDtos = null;
////        try {
////            reviewDtos = future1.get(TASK_TIME, TimeUnit.SECONDS);
////            System.out.println(future3.isDone()); //False
////            log.info("Result of Future 3 object:: ", executorService);
////        } catch (InterruptedException | ExecutionException e) {
////            throw new RuntimeException(e);
////        } catch (TimeoutException timeoutException) {
////            future1.cancel(true);
////        }
////        movieDetailsDto.setReviews(reviewDtos);
////        Set<CountryDto> countryDtos = null;
////        try {
////            countryDtos = future2.get(TASK_TIME, TimeUnit.SECONDS);
////            System.out.println(executorService);
////        } catch (InterruptedException | ExecutionException e) {
////            throw new RuntimeException(e);
////        } catch (TimeoutException timeoutException) {
////            future2.cancel(true);
////        }
////        movieDetailsDto.setCountries(countryDtos);
////        Set<GenreDto> genreDtos = null;
////        try {
////            genreDtos = future3.get(TASK_TIME, TimeUnit.SECONDS);
////            System.out.println(executorService);
////        } catch (InterruptedException | ExecutionException e) {
////            throw new RuntimeException(e);
////        } catch (TimeoutException timeoutException) {
////            future3.cancel(true);
////        }
////        movieDetailsDto.setGenres(genreDtos);
//
//
////        var tasks = IntStream.rangeClosed(1, 1_000_000).mapToObj(i -> task).collect(toList());
////        executorService.invokeAll(futureList);
////___
//
////in for: submit, list.add // 2 for : get //shutdown //add logs
//
////        List<Future<?>> list = new ArrayList<>();
////        list.add(future1);
////        list.add(future2);
////        list.add(future3);
////
////        for (Future<?> future : list) {
////            try {
////
////
////        }
////        for (Future future : list) {
////
////            Object o = future.get();
////
////        } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            } catch (ExecutionException e) {
////                throw new RuntimeException(e);
////            }
////        }
//
////        try {
//
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        } catch (ExecutionException e) {
////            throw new RuntimeException(e);
////        }
//
//        executorService.shutdown();
//        log.info("Thread main finished");
//        return movieDetailsDto;
//
//
//    }
//
//    private void processResult(MovieDetailsDto movieDetailsDto, Future<MovieDetailsDto> future)
//            throws InterruptedException, ExecutionException {
//
//
//        MovieDetailsDto result = null;
//        try {
//// выводим возвращаемое значение Future, замечаем задержку вывода в консоли
//// потому что Future.get() ожидает завершения задачи
//            result = future.get(5, TimeUnit.SECONDS);
//
//            System.out.println(new Date()+ "::"+result);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            throw new RuntimeException(e);
//        }
////        try {
////            result = future.get(1, TimeUnit.SECONDS);
////        } catch (TimeoutException e) {
////            future.cancel(true);
////        } catch (Exception e) {
////            // handle other exceptions
////        } finally {
////            executorService.shutdownNow();
////        }
//
//        movieDetailsDto.setCountries(result.getCountries());
//        movieDetailsDto.setGenres(result.getGenres());
//        movieDetailsDto.setReviews(result.getReviews());
//
//    }
//
////    Set<Genre> enrichGenres(GenreCallable genreCallable)  {
////        Future<Set<GenreDto>> future =  executorService.submit(genreCallable);
////        try {
////            return future.get();
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        } catch (ExecutionException e) {
////            throw new RuntimeException(e);
////        }
//////        executorService.shutdown();
////
////    }
//}
