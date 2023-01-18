package com.bondarenko.movieland.service;

import com.bondarenko.movieland.service.entity.common.TaskResult;

import java.util.List;
import java.util.concurrent.Future;

public interface FutureService {
    List<Future<TaskResult>> getFuturesList(int movieId,TaskResult taskResult);
}