package com.logger.sink;

import com.logger.exception.SinkInitException;

public class DefaultSinkFactory implements SinkFactory {

    public Sink getInstance(final SinkConfig config) {
        try {
            final String sinkType = config.getSinkType();
            if (SinkType.TextFile.value.equals(sinkType)) {
                return new TextFileSink((TextFileSinkConfig) config);
            } else if (SinkType.Console.value.equals(sinkType)) {
                return new ConsoleSink(config);
            }

            return null;
        } catch (Exception exception) {
            throw new SinkInitException("Failed to get sink instance", exception);
        }
    }
}
