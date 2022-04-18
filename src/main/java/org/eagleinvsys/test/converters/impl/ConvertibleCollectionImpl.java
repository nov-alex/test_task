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
        this.headers = calculateHeaders();
    }

    private List<String> calculateHeaders() {
        if (collection.isEmpty()) {
            return List.of();
        } else {
            return collection.get(0).keySet().stream().toList();
        }
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
