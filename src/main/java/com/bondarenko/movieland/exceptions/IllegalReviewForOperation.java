package com.bondarenko.movieland.exceptions;

public class IllegalReviewForOperation extends RuntimeException {

    private static final String ILLEGAL_REVIEW_MESSAGE = "Review has already existed: %s with description: %s";

    public IllegalReviewForOperation(String description) {
        super(String.format(ILLEGAL_REVIEW_MESSAGE, description));
    }
}