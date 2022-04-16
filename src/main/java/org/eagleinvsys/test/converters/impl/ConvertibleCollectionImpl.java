package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.List;
import java.util.Map;

public class ConvertibleCollectionImpl implements ConvertibleCollection {

    private final List<Map<String, String>> collection;
    private List<String> headers;

    public ConvertibleCollectionImpl(List<Map<String, String>> collection) {
        this.collection = collection;
    }

    @Override
    public List<String> getHeaders() {
        if (headers != null) {
            return headers;
        }
        List<String> tempHeaders = List.of();
        if (!collection.isEmpty()) {
            tempHeaders = collection.get(0).keySet().stream().toList();
        }
        headers = tempHeaders;
        return headers;
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return () -> new ConvertibleMessageIterator(collection);
    }
}
