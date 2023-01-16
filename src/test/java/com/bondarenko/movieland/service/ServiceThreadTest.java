//package com.bondarenko.movieland.service;
//
//import com.bondarenko.movieland.AbstractBaseITest;
//import com.bondarenko.movieland.dto.GenreDto;
//import com.bondarenko.movieland.dto.ReviewDto;
//import com.bondarenko.movieland.service.impl.callable.CompletableFutures;
//import com.bondarenko.movieland.service.impl.callable.CountryCallable;
//import com.bondarenko.movieland.service.impl.callable.GenreCallable;
//import com.bondarenko.movieland.service.impl.callable.ReviewCallable;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//
//import java.util.Set;
//import java.util.concurrent.*;
//
//import static org.junit.Assert.assertTrue;
//
//@AutoConfigureMockMvc(addFilters = false)
//public class ServiceThreadTest extends AbstractBaseITest {
//    @Autowired
//    private ExecutorService executorService;
//    @Autowired
//    private ReviewService reviewService;
//    @Autowired
//    private GenreService genreService;
//
//    @Autowired
//    private CountryService countryService;
//    @Autowired
//    CompletableFutures completableFuturesl;
//
//    @Test
//    @DisplayName("validate Queries after Find All Movies")
//    void validateQueries_afterFindAllMovies() throws ExecutionException, InterruptedException, TimeoutException {
//        ReviewCallable reviewCallable = new ReviewCallable(reviewService, 2);
//        GenreCallable genreCallable = new GenreCallable(genreService, 2);
//        CountryCallable countryCallable = new CountryCallable(countryService, 2);
//
//
//        Future<Set<ReviewDto>> future2 = executorService.submit(reviewCallable);
//        Future<Set<GenreDto>> future1 = executorService.submit(genreCallable);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////        List<Future<Task>> futures = executorService.invokeAll(callableTasks, 5, TimeUnit.SECONDS);
////
////        System.out.println(executorService);    //(2)
////
////
//////        Thread t=new Thread(futureTask);
////        Future<Set<CountryDto>> future2 = executorService.submit(countryCallable);
////        System.out.println(executorService);
////        Future<Set<GenreDto>> future3 = executorService.submit(genreCallable);
////        System.out.println(future3.isDone()); //False
////        System.out.println(executorService);
////        movieService.findAll(movieRequest);
////        assertSelectCount(1);
//
//        future2.get(5, TimeUnit.SECONDS);
//        future1.get(5, TimeUnit.SECONDS);
//
//        assertTrue(future2.isDone());
//        assertTrue(future1.isDone());
////        assertTrue(future3.isDone());
//    }
//}

//@Test
//public void testListGetsFilled() throws Exception {
//        List<String> list = Collections.emptyList();
//
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//        while(true) {
//        if(!list.isEmpty()) {
//        return list.size();
//        }
//        }
//        });
//
//        // ... do some async task that should fill the list in less than 1 second ...
//
//        assertThat(future.get(1, TimeUnit.SECONDS)).isGreaterThan(0);
//        }