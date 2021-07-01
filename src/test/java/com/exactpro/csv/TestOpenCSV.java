package com.exactpro.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvException;

public class TestOpenCSV {

    @Test
    public void testEquals() throws IOException, CsvException {

        List<String[]> data = new ArrayList<>();
        data.add(new String[] { "Number", "Letter", "Word", "Sentence" });
        data.add(new String[] { "1", "A", "One", "Line\nAnd another Line" });
        data.add(new String[] { "2", "B", "Two", "\"I'm being quoted\"" });

        File file = new File("src/test/resources/test_opencsv.csv");

        OpenCSV openCSV = new OpenCSV();

        openCSV.write(data, file);

        List<String[]> collectedData = openCSV.read(file);

        Assertions.assertArrayEquals(data.toArray(), collectedData.toArray());
    }

}
