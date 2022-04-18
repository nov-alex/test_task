package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        CsvFormatter csvFormatter = new CsvFormatterImpl();
        try {
            outputStream.write(formatHeaders(collectionToConvert, csvFormatter));
            for (ConvertibleMessage convertibleMessage : collectionToConvert.getRecords()) {
                outputStream.write(formatRecord(convertibleMessage, collectionToConvert.getHeaders(), csvFormatter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] formatHeaders(ConvertibleCollection collectionToConvert, CsvFormatter formatter) {
        return formatter.formatAsLine(collectionToConvert.getHeaders()).getBytes(StandardCharsets.UTF_8);
    }

    private byte[] formatRecord(ConvertibleMessage convertibleMessage, Collection<String> headers, CsvFormatter formatter) {
        List<String> list = new ArrayList<>();
        for (String header : headers) {
            list.add(convertibleMessage.getElement(header));
        }
        return formatter.formatAsLine(list).getBytes(StandardCharsets.UTF_8);
    }

}