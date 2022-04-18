package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConvertibleCollectionImplTest {

    private final Map<String, String> rowMap1 = new LinkedHashMap<>();
    private final Map<String, String> rowMap2 = new LinkedHashMap<>();
    private final Map<String, String> rowMap3 = new LinkedHashMap<>();
    private final List<Map<String, String>> collection = new ArrayList<>();
    private final List<String> headers = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        String header;
        for (int i = 1; i <= 3; i++) {
            header = "header" + i;
            rowMap1.put(header, "value1" + i);
            rowMap2.put(header, "value2" + i);
            rowMap3.put(header, "value3" + i);

            headers.add(header);
        }

        collection.add(rowMap1);
        collection.add(rowMap2);
        collection.add(rowMap3);

    }

    @Test
    void givenCollection_whenGetHeaders_thenCorrectlyReturned() {
        ConvertibleCollectionImpl convertibleCollection = new ConvertibleCollectionImpl(collection);
        Assertions.assertEquals(headers, convertibleCollection.getHeaders());
    }

    @Test
    void givenCollection_whenGetRecords_thenCorrectlyReturned() {
        ConvertibleCollectionImpl convertibleCollection = new ConvertibleCollectionImpl(collection);
        int j = 1;
        Map<String, String> rowMap;

        for (ConvertibleMessage message : convertibleCollection.getRecords()) {
            rowMap = switch (j) {
                case 1 -> rowMap1;
                case 2 -> rowMap2;
                case 3 -> rowMap3;
                default -> null;
            };
            Assertions.assertNotNull(rowMap);
            for (int i = 1; i <= 3; i++) {
                Assertions.assertEquals(rowMap.get("header" + i), message.getElement("header" + i));
            }
            j++;
        }
    }
}