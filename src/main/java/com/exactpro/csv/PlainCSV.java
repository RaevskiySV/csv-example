package com.exactpro.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlainCSV {
    private static final String COMMA = ",";
    private static final String NO_QUOTE = "";

    private static final String DOUBLE_QUOTES = "\"";
    private static final String EMBEDDED_DOUBLE_QUOTES = "\"\"";

    private String separator;
    private boolean removeNewlines;

    public PlainCSV() {
        this(COMMA);
    }

    public PlainCSV(String separator) {
        this(separator, false);
    }

    public PlainCSV(String separator, boolean removeNewlines) {
        this.separator = separator;
        this.removeNewlines = removeNewlines;
    }

    public void write(List<String[]> list, File file) throws IOException {

        List<String> collect = list.stream()
                .map(this::convert)
                .collect(Collectors.toList());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : collect) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public List<String[]> read(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines().map(row -> row.split(separator)).collect(Collectors.toList());
        }
    }

    public String convert(String[] line) {
        return Stream.of(line)
                .map(this::formatField)
                .collect(Collectors.joining(separator));

    }

    private String formatField(String field) {

        String result = field;

        if (removeNewlines) {
            result = result.replaceAll("\\R", " ");
        }

        result = result.replace(DOUBLE_QUOTES, EMBEDDED_DOUBLE_QUOTES);

        if (result.matches(".*[^\\w\\d\\s].*") || result.matches(".*\n.*")) {
            result = DOUBLE_QUOTES + result + DOUBLE_QUOTES;
        }
        return result;
    }
}
