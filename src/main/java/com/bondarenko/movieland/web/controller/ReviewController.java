package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.service.dto.request.ReviewRequestDto;
import com.bondarenko.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public void add(@RequestBody ReviewRequestDto reviewDto) {
        reviewService.add(reviewDto);
    }
}