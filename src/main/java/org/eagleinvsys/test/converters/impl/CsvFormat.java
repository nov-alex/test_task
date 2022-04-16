package org.eagleinvsys.test.converters.impl;

import java.util.Collection;

public interface CsvFormat {

    /**
     * Formats given {@link Collection<String>} and return result as a text
     *
     * @param collection collection to format
     * @return csv-formatted string
     */
    String format(Collection<String> collection);

    /**
     * Formats given {@link Collection<String>} and return result as a line with CR
     *
     * @param collection collection to format
     * @return csv-formatted line
     */
    String formatAsLine(Collection<String> collection);

}
