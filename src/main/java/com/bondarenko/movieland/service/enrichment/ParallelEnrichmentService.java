package com.bondarenko.movieland.service.enrichment;

import com.bondarenko.movieland.exceptions.ThreadInterruptedException;
import com.bondarenko.movieland.service.EnrichmentService;
import com.bondarenko.movieland.service.FutureService;
import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;
import com.bondarenko.movieland.service.entity.common.EnrichmentTaskResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@Primary
@AllArgsConstructor
public class ParallelEnrichmentService implements EnrichmentService {
    private static final long TASK_TIMEOUT_SECONDS = 5;
    private FutureService futureService;

    @Override
    public MovieDetailsDto enrichMovieDetailsDto(MovieDetailsDto movieDetailsDto) {
        int movieId = movieDetailsDto.getId();
        EnrichmentTaskResult enrichmentTaskResult = new EnrichmentTaskResult();
        List<Future<EnrichmentTaskResult>> futuresList = futureService.getFuturesList(movieId, enrichmentTaskResult);

        for (Future<EnrichmentTaskResult> future : futuresList) {
            try {
                EnrichmentTaskResult result = future.get(TASK_TIMEOUT_SECONDS, TimeUnit.SECONDS);
                enrichWithTaskResult(movieDetailsDto, result);
            } catch (TimeoutException e) {
                future.cancel(true);
            } catch (ExecutionException | InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ThreadInterruptedException(Thread.currentThread());
            }
        }
        return movieDetailsDto;
    }

    private void enrichWithTaskResult(MovieDetailsDto movieDetailsDto, EnrichmentTaskResult enrichmentTaskResult) {
        movieDetailsDto.setReviews(enrichmentTaskResult.getReviewDtos());
        movieDetailsDto.setGenres(enrichmentTaskResult.getGenreDtos());
        movieDetailsDto.setCountries(enrichmentTaskResult.getCountryDtos());
    }
}