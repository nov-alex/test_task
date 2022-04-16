package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConvertibleMessageIterator implements Iterator<ConvertibleMessage> {

    private final Iterator<Map<String, String>> iterator;

    public ConvertibleMessageIterator(List<Map<String, String>> collection) {
        this.iterator = collection.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ConvertibleMessage next() {
        Map<String, String> map = iterator.next();
        return new ConvertibleMessageImpl(map);
    }
}
