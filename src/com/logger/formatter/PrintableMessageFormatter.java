package com.logger.formatter;

import com.logger.Message;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class PrintableMessageFormatter implements MessageFormatter<String> {

    private final DateTimeFormatter dateTimeFormatter;

    public PrintableMessageFormatter(final String timestampFormat) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(timestampFormat)
                .withZone(ZoneId.from(ZoneOffset.UTC));
    }

    @Override
    public String format(final Message message) {
        final String formattedTimestamp = dateTimeFormatter.format(message.getTimeStamp());

        return formattedTimestamp +
                " : " +
                "[" + message.getLogLevel() + "]" +
                " : " + message.getNamespace() + " : " +
                message.getContent();
    }
}
