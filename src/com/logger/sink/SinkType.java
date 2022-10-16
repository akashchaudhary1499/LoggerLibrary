package com.logger.sink;

public enum SinkType {
    TextFile("TextFile"),
    Console("Console"),
    Database("Database");

    public final String value;

    SinkType(final String value) {
        this.value = value;
    }
}
