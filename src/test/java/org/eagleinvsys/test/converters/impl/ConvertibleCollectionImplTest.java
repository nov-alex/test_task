package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConvertibleCollectionImplTest {

    private final Map<String, String> map1 = new LinkedHashMap<>();
    private final Map<String, String> map2 = new LinkedHashMap<>();
    private final Map<String, String> map3 = new LinkedHashMap<>();
    private final List<Map<String, String>> collection = new ArrayList<>();
    private final List<String> headers = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        for (int i = 1; i <= 3; i++) {
            map1.put("header" + i, "value1" + i);
            map2.put("header" + i, "value2" + i);
            map3.put("header" + i, "value3" + i);

            headers.add("header" + i);
        }

        collection.add(map1);
        collection.add(map2);
        collection.add(map3);

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
        Map<String, String> map;

        for (ConvertibleMessage message : convertibleCollection.getRecords()) {
            map = switch (j) {
                case 1 -> map1;
                case 2 -> map2;
                case 3 -> map3;
                default -> null;
            };
            Assertions.assertNotNull(map);
            for (int i = 1; i <= 3; i++) {
                Assertions.assertEquals(map.get("header" + i), message.getElement("header" + i));
            }
            j++;
        }
    }
}