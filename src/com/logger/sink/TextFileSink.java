package com.logger.sink;

import com.logger.Message;
import com.logger.Utils.FileUtils;
import com.logger.formatter.MessageFormatter;
import com.logger.formatter.PrintableMessageFormatter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class TextFileSink implements Sink {

    private final long fileSizeThreshold; // in bytes
    private final File logDirectory;
    private final File logFile;
    private BufferedWriter logWriter;
    private final MessageFormatter<String> messageFormatter;


    public TextFileSink(final TextFileSinkConfig config) throws IOException {
        this.logFile = new File(config.getFileLocation());
        this.logDirectory = logFile.getParentFile();
        if(!logDirectory.exists()) {
            logDirectory.mkdirs();
        }

        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        this.logWriter = new BufferedWriter(new FileWriter(logFile, true));
        this.fileSizeThreshold = config.getFileSizeThreshold();
        this.messageFormatter = new PrintableMessageFormatter(config.getTimestampFormat());
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (logWriter != null) {
            logWriter.close();
        }
    }

    @Override
    public void processMessage(final Message message) {
        try {
            if (isFileSizeExceedThreshold()) {
                rotateLogFile();
            }

            final String formattedMessage = messageFormatter.format(message);
            logWriter.write(formattedMessage);
            logWriter.write("\n");
            logWriter.flush();
        } catch (Exception ex) {
            System.err.println("Error logging");
        }
    }

    private boolean isFileSizeExceedThreshold() throws IOException {
        return Files.size(logFile.toPath()) >= fileSizeThreshold;
    }

    private void rotateLogFile() throws IOException {
        final Path target = getNextCompressedFilePath();
        try {
            this.logWriter.close();
            FileUtils.compressFile(logFile, target.toFile());
            this.logWriter = new BufferedWriter(new FileWriter(logFile, false));
        } catch (Exception e) {
            this.logWriter = new BufferedWriter(new FileWriter(logFile, true));
        }
    }

    private Path getNextCompressedFilePath() {
        final String logFileName = logFile.getName();
        int count = Objects.requireNonNull(logDirectory.listFiles((dir, name) -> name.startsWith(logFileName)))
                .length;

        return Paths.get(logFile.getPath() + "." + count + ".gz");
    }
}
