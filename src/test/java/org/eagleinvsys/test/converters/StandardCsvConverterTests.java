package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class StandardCsvConverterTests {

    @Test
    void givenCollection_thenCorrectConvert() {
        List<Map<String, String>> collection = new ArrayList<>();
        Map<String, String> rowMap1 = new LinkedHashMap<>();
        Map<String, String> rowMap2 = new LinkedHashMap<>();

        rowMap1.put("header1", "value11");
        rowMap1.put("header2", "value12");

        rowMap2.put("header1", "value21");
        rowMap2.put("header2", "value22");

        collection.add(rowMap1);
        collection.add(rowMap2);

        String expected = """
                "header1","header2"\r
                "value11","value12"\r
                "value21","value22"\r
                """;

        CsvConverter csvConverter = new CsvConverter();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StandardCsvConverter converter = new StandardCsvConverter(csvConverter);

        converter.convert(collection, byteArrayOutputStream);

        Assertions.assertEquals(expected, byteArrayOutputStream.toString());
    }
}