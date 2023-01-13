package com.bondarenko.movieland.service.impl;

import com.bondarenko.movieland.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultEnrichmentService {

    private ExecutorService executorService;

    Set<ReviewDto> enrichReviews(ReviewCallable reviewCallable)  {
        Future<Set<ReviewDto>> future =  executorService.submit(reviewCallable);
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
//        executorService.shutdown();

    }
}
