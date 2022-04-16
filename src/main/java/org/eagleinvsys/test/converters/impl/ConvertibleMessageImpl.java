package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.Map;

public class ConvertibleMessageImpl implements ConvertibleMessage {

    private final Map<String, String> map;

    public ConvertibleMessageImpl(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String getElement(String elementId) {
        return map.get(elementId);
    }
}
