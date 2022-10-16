package com.logger.sink;

import com.logger.Message;

public interface Sink {
    void processMessage(Message message);
}
