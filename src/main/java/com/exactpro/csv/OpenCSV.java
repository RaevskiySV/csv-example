package com.exactpro.csv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvException;

public class OpenCSV {

    private static final char COMMA = ',';
    private static final char DOUBLE_QUOTES = '"';
    private static final char NO_QUOTE_CHARACTER = '\u0000';

    private char separator;
    private char quote;

    public OpenCSV() {
        this(COMMA);
    }

    public OpenCSV(char separator) {
        this(separator, DOUBLE_QUOTES);
    }

    public OpenCSV(char separator, char quote) {
        this.separator = separator;
        this.quote = quote;
    }

    public void write(List<String[]> data, File file) throws IOException {
        try (ICSVWriter writer = new CSVWriterBuilder(
                new FileWriter(file))
                .withSeparator(separator)
                .withQuoteChar(quote)
                .build()) {
            writer.writeAll(data);
        }
    }

    public List<String[]> read(File file) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            return csvReader.readAll();
        }
    }
}
