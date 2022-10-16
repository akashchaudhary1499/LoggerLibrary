package com.logger.formatter;

import com.logger.Message;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PrintableMessageFormatter implements MessageFormatter<String> {

    private final DateTimeFormatter dateTimeFormatter;

    public PrintableMessageFormatter(final String timestampFormat) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(timestampFormat)
                .withZone(ZoneId.from(ZoneOffset.UTC));
    }

    @Override
    public String format(final Message message) {
        final String formattedTimestamp = dateTimeFormatter.format(message.getTimeStamp());
        final StringBuilder sb = new StringBuilder();
        sb.append(formattedTimestamp).append(" : ").append("[").append(message.getLogLevel()).append("] : ");

        for(Map.Entry<String, String> entry : message.getAdditionalContext().entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(" ");
        }

        sb.append(" : ").append(message.getContent());

        return sb.toString();
    }
}
