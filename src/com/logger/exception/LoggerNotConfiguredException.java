package com.logger.exception;

public class LoggerNotConfiguredException extends RuntimeException {

    public LoggerNotConfiguredException(String message) {
        super(message);
    }
}
