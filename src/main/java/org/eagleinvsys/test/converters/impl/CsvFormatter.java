package org.eagleinvsys.test.converters.impl;

import java.util.Collection;
import java.util.stream.Collectors;

public class CsvFormatter implements CsvFormat {
    public static final String DELIMITER = ",";
    public static final String QUOTE = "\"";
    public static final String RECORDSEPARATOR = "\r\n";

    @Override
    public String format(Collection<String> collection) {
        return collection.stream()
                .map(s -> {
                    String escaped = s.replace(QUOTE, QUOTE + QUOTE);
                    return QUOTE + escaped + QUOTE;
                })
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public String formatAsLine(Collection<String> collection) {
        String line = format(collection);
        if ("".equals(line)) {
            return "";
        }
        return format(collection) + RECORDSEPARATOR;
    }
}
