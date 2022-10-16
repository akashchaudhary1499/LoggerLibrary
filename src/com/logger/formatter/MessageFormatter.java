package com.logger.formatter;

import com.logger.Message;

public interface MessageFormatter<T> {
    T format(Message message);
}
