package org.eagleinvsys.test.converters.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class CsvFormatterImplTest {
    @ParameterizedTest
    @MethodSource("provideCollectionForCsvFormatter")
    void givenCollection_thenCorrectlyFormatted(Collection<String> collection, String expected) {
        CsvFormatter CsvFormatter = new CsvFormatterImpl();
        String result = CsvFormatter.format(collection);
        Assertions.assertEquals(result, expected);
    }

    private static Stream<Arguments> provideCollectionForCsvFormatter() {
        return Stream.of(
                Arguments.of(List.of("header1", "header2"), "\"header1\",\"header2\""),
                Arguments.of(List.of("\"header need escaping", "header2"), "\"\"\"header need escaping\",\"header2\""),
                Arguments.of(List.of("header \"need\" escaping", "header2"), "\"header \"\"need\"\" escaping\",\"header2\"")
        );
    }
}