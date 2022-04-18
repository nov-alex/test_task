package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.List;
import java.util.Map;

public class ConvertibleCollectionImpl implements ConvertibleCollection {

    private final List<Map<String, String>> collection;
    private final List<String> headers;

    public ConvertibleCollectionImpl(List<Map<String, String>> collection) {
        this.collection = collection;
        this.headers = calculateHeaders(collection);
    }

    private List<String> calculateHeaders(List<Map<String, String>> collection) {
        List<String> collHeaders;
        if (collection.isEmpty()) {
            collHeaders = List.of();
        } else {
            collHeaders = collection.get(0).keySet().stream().toList();
        }
        return collHeaders;
    }

    @Override
    public List<String> getHeaders() {
        return headers;
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return () -> new ConvertibleMessageIterator(collection);
    }
}
