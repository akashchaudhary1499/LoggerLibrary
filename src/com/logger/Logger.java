package com.logger;

import com.logger.exception.LoggerNotConfiguredException;
import com.logger.sink.DefaultSinkFactory;
import com.logger.sink.Sink;
import com.logger.sink.SinkConfig;
import com.logger.sink.SinkFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {

    private static Logger instance;

    private final Map<LogLevel, List<Sink>> sinksByLogLevel;
    private final SinkFactory sinkFactory;

    private Logger(final List<SinkConfig> configs, final SinkFactory sinkFactory) {
        this.sinksByLogLevel = new HashMap<>();
        for (final LogLevel logLevel : LogLevel.values()) {
            sinksByLogLevel.put(logLevel, new ArrayList<>());
        }

        this.sinkFactory = sinkFactory;
        initializeSinks(configs);
    }

    private void initializeSinks(final List<SinkConfig> configs) {
        for (final SinkConfig config : configs) {
            final Sink sink = sinkFactory.getInstance(config);
            for (LogLevel logLevel : config.getSupportedLogLevels()) {
                sinksByLogLevel.get(logLevel).add(sink);
            }
        }
    }

    public static void configure(final List<SinkConfig> configs) {
        configure(configs, new DefaultSinkFactory());
    }

    public static void configure(final List<SinkConfig> configs, final SinkFactory sinkFactory) {
        Logger.instance = new Logger(configs, sinkFactory);
    }


    public static Logger getInstance() {
        if (instance == null) {
            throw new LoggerNotConfiguredException("No logger instance available. Configure logger first.");
        }

        return instance;
    }

    public void log(final String content, final LogLevel logLevel, final String namespace) {
        final Message message = new Message(content, namespace, logLevel, Instant.now());
        final List<Sink> sinks = sinksByLogLevel.get(logLevel);
        for (final Sink sink : sinks) {
            sink.processMessage(message);
        }
    }
}
