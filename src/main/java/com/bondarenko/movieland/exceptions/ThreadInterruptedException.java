package com.bondarenko.movieland.exceptions;

public class ThreadInterruptedException extends RuntimeException {
    private static final String TREAD_INTERRUPTED_AFTER_TIMEOUT_MESSAGE = "Thread interrupted, execution time is over: %s";

    public ThreadInterruptedException(Thread thread) {
        super(String.format(TREAD_INTERRUPTED_AFTER_TIMEOUT_MESSAGE, thread.getName()));
    }
}