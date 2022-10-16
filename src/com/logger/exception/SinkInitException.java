package com.logger.exception;

public class SinkInitException extends RuntimeException {

    public SinkInitException(final String message, Throwable throwable) {
        super(message, throwable);
    }
}
