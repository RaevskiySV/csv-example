package com.exactpro.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPlainCSV {

    @Test
    public void testEquals() throws IOException {

        List<String[]> data = new ArrayList<>();
        data.add(new String[] { "Number", "Letter", "Word", "Sentence" });
        data.add(new String[] { "1", "A", "One", "First word and second word" });
        data.add(new String[] { "2", "B", "Two", "Second sentence" });

        File file = new File("src/test/resources/test_plaincsv.csv");

        PlainCSV plainCSV = new PlainCSV();

        plainCSV.write(data, file);

        List<String[]> collectedData = plainCSV.read(file);

        Assertions.assertArrayEquals(data.toArray(), collectedData.toArray());
    }
}
