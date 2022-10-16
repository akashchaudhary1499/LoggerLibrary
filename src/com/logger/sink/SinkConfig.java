package com.logger.sink;

import com.logger.LogLevel;

import java.util.List;

public class SinkConfig {
    private final String timestampFormat, sinkType;
    private final List<LogLevel> supportedLogLevels;

    public SinkConfig(final String timestampFormat, final String sinkType, final List<LogLevel> supportedLogLevels) {
        this.sinkType = sinkType;
        this.timestampFormat = timestampFormat;
        this.supportedLogLevels = supportedLogLevels;
    }

    public List<LogLevel> getSupportedLogLevels() {
        return supportedLogLevels;
    }

    public String getSinkType() {
        return sinkType;
    }

    public String getTimestampFormat() {
        return timestampFormat;
    }
}
