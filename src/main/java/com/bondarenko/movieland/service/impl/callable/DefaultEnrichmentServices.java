package com.bondarenko.movieland.service.impl.callable;

import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultEnrichmentServices {
    private final long TASK_TIME = 5;

    private ExecutorService executorService;

    private CompletableFutures completableFutures;

    public MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto, int id) {

        CompletableFuture<MovieDetailsDto> future1 = completableFutures.getReviewDtos(id, movieDetailsDto);
        CompletableFuture<MovieDetailsDto> future2 = completableFutures.getCountryDtos(id, movieDetailsDto);
        CompletableFuture<MovieDetailsDto> future3 = completableFutures.getGenreDtos(id, movieDetailsDto);
        List<CompletableFuture<MovieDetailsDto>> futuresList = List.of(future1, future2, future3);

        CompletableFuture<Void> allFuturesResult = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));
        try {
            allFuturesResult.get(1, TimeUnit.MILLISECONDS);
        }catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }  catch (TimeoutException | ExecutionException e) {
            allFuturesResult.cancel(false);
        }
        return futuresList
                .stream()
                .filter(future -> future.isDone() && !future.isCompletedExceptionally()) // keep only the ones completed
                .map(CompletableFuture::join) // get the value from the completed future
                .findFirst().get(); // collect as a list


//        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
//        CompletableFuture<List<MovieDetailsDto>> a = allOf(futuresList);
//
//
//        List<MovieDetailsDto> movieDetailsDtos=null;
//        try {
//            movieDetailsDtos = a.get(5, TimeUnit.SECONDS);
//
//        } catch (InterruptedException | ExecutionException | TimeoutException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//        return movieDetailsDtos;
    }


}
