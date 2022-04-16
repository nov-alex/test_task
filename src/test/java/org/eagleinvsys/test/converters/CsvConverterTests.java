package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;

class CsvConverterTests {

    private AutoCloseable closeable;
    @Mock
    private ConvertibleCollection convertibleCollection;
    @Mock
    private ConvertibleMessage convertibleMessage;
    @Mock
    private Iterable<ConvertibleMessage> iterable;
    @Mock
    private Iterator<ConvertibleMessage> iterator;

    @BeforeEach
    void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void givenMockedConvertibleCollection_thenCorrectConvert() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CsvConverter csvConverter = new CsvConverter();

        Mockito.when(iterable.iterator()).thenReturn(iterator);
        Mockito.when(iterator.hasNext()).thenReturn(true, false);
        Mockito.when(iterator.next()).thenReturn(convertibleMessage);
        Mockito.when(convertibleMessage.getElement("header1")).thenReturn("value1");
        Mockito.when(convertibleCollection.getHeaders()).thenReturn(List.of("header1"));
        Mockito.when(convertibleCollection.getRecords()).thenReturn(iterable);

        csvConverter.convert(convertibleCollection, byteArrayOutputStream);

        Assertions.assertEquals("\"header1\"\r\n\"value1\"\r\n", byteArrayOutputStream.toString());
    }
}