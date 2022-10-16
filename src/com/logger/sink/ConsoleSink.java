package com.logger.sink;

import com.logger.Message;
import com.logger.formatter.MessageFormatter;
import com.logger.formatter.PrintableMessageFormatter;

public class ConsoleSink implements Sink {

    private final MessageFormatter<String> messageFormatter;

    public ConsoleSink(final SinkConfig config) {
        this.messageFormatter = new PrintableMessageFormatter(config.getTimestampFormat());
    }

    @Override
    public void processMessage(final Message message) {
        System.out.println(messageFormatter.format(message));
    }
}
