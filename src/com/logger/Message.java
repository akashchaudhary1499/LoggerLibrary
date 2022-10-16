package com.logger;

import java.time.Instant;

public class Message {
    private final String content, namespace;
    private final LogLevel logLevel;
    private final Instant timeStamp;

    public Message(final String content, final String namespace, final LogLevel logLevel, final Instant timeStamp) {
        this.content = content;
        this.namespace = namespace;
        this.logLevel = logLevel;
        this.timeStamp = timeStamp;
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
}
