package com.logger.sink;

public interface SinkFactory {
    Sink getInstance(SinkConfig config);
}
