package com.logger.sink;

import com.logger.LogLevel;

import java.util.List;

public class TextFileSinkConfig extends SinkConfig{

    private final String fileLocation;
    private final long fileSizeThreshold; //Bytes

    public TextFileSinkConfig(final String timestampFormat, final String sinkType,
                              final List<LogLevel> supportedLogLevels, final String fileLocation,
                              final int fileSizeThreshold) {
        super(timestampFormat, sinkType, supportedLogLevels);
        this.fileLocation = fileLocation;
        this.fileSizeThreshold = fileSizeThreshold;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public long getFileSizeThreshold() {
        return fileSizeThreshold;
    }
}
