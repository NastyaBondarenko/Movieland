package com.bondarenko.movieland.service;

import com.bondarenko.movieland.service.dto.request.MovieDetailsDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CompletableFutureService {
    List<CompletableFuture<MovieDetailsDto>> getCompletableFuturesList(int movieId, MovieDetailsDto movieDetailsDto);
}