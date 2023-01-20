package com.bondarenko.movieland.service;

import com.bondarenko.movieland.service.entity.common.EnrichmentTaskResult;

import java.util.List;
import java.util.concurrent.Future;

public interface FutureService {
    List<Future<EnrichmentTaskResult>> getFuturesList(int movieId, EnrichmentTaskResult enrichmentTaskResult);
}