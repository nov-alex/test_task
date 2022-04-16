package org.eagleinvsys.test.converters.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class CsvFormatterTest {
    @Test
    void givenCollection_whenNoEscapingNeeded_thenCorrectlyFormatted() {
        Collection<String> collection = List.of("header1", "header2");
        String expected = "\"header1\",\"header2\"";
        CsvFormat CsvFormat = new CsvFormatter();
        String result = CsvFormat.format(collection);
        Assertions.assertEquals(result, expected);
    }

    @Test
    void givenCollection_whenEscapingNeeded_thenCorrectlyFormatted() {
        Collection<String> collection = List.of("\"header need escaping", "header2");
        String expected = "\"\"\"header need escaping\",\"header2\"";
        CsvFormat CsvFormat = new CsvFormatter();
        String result = CsvFormat.format(collection);
        Assertions.assertEquals(result, expected);
    }
}