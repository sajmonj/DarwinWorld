package org.example.data;


import java.util.List;
import java.util.StringJoiner;

public class CsvGenerator {

    public static String generateCsvLine(List<String> statisticsList) {
        StringJoiner csvLine = new StringJoiner(",");

        for (String stat : statisticsList) {
            csvLine.add(stat);
        }

        return csvLine.toString();
    }
}

