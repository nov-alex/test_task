package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

class StandardCsvConverterTests {

    private AutoCloseable closeable;

    @Spy
    private ArrayList<Map<String, String>> collection;

    @Spy
    LinkedHashMap<String, String> map1;

    @Spy
    LinkedHashMap<String, String> map2;

    @BeforeEach
    void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void givenCollection_thenCorrectConvert() {

        map1.put("header1", "value11");
        map1.put("header2", "value12");

        map2.put("header1", "\"value21");
        map2.put("header2", "value22");

        collection.add(map1);
        collection.add(map2);

        String expected = """
                "header1","header2"\r
                "value11","value12"\r
                ""\"value21","value22"\r
                """;

        CsvConverter csvConverter = new CsvConverter();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StandardCsvConverter converter = new StandardCsvConverter(csvConverter);

        converter.convert(collection, byteArrayOutputStream);

        Assertions.assertEquals(expected, byteArrayOutputStream.toString());
    }
}