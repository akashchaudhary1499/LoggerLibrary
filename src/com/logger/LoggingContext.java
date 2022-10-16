package com.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class LoggingContext {

    private final Map<String, String> globalContext;
    private final Map<Long, Map<String, String>> contextByThreadId;

    LoggingContext() {
        this.globalContext = new ConcurrentHashMap<>();
        this.contextByThreadId = new ConcurrentHashMap<>();
    }

    void setGlobalContext(final String propertyName, final String propertyValue) {
        globalContext.put(propertyName, propertyValue);
    }

    void removeGlobalContext(final String propertyName) {
        globalContext.remove(propertyName);
    }

    void setThreadContext(final long threadId, final String propertyName, final String propertyValue) {
        final Map<String, String> threadContext = contextByThreadId.computeIfAbsent(threadId, k -> new HashMap<>());
        threadContext.put(propertyName, propertyValue);
    }

    void removeThreadContext(final long threadId, final String propertyName) {
        Map<String, String> threadContext = contextByThreadId.computeIfAbsent(threadId, k -> new HashMap<>());
        threadContext.remove(propertyName);
    }

    Map<String, String> getLoggingContext(final long threadId) {
        Map<String, String> context = new HashMap<>(globalContext);
        if(contextByThreadId.get(threadId) != null) {
            context.putAll(contextByThreadId.get(threadId));
        }
        return context;
    }
}
