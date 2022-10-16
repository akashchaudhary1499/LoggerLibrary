package com.logger;

import java.time.Instant;
import java.util.Map;

public class Message {
    private final String content, namespace;
    private final LogLevel logLevel;
    private final Instant timeStamp;
    private final Map<String, String> additionalContext;

    public Message(final String content, final String namespace, final LogLevel logLevel, final Instant timeStamp,
                   final Map<String, String> additionalContext) {
        this.content = content;
        this.namespace = namespace;
        this.logLevel = logLevel;
        this.timeStamp = timeStamp;
        this.additionalContext = additionalContext;
    }

    public String getContent() {
        return content;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public String getNamespace() {
        return namespace;
    }

    public Map<String, String> getAdditionalContext() {
        return additionalContext;
    }
}
