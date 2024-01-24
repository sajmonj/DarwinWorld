package org.example.data;


import java.util.Map;
import java.util.StringJoiner;

public class CsvGenerator {

    public static String generateCsvLine(Map<Statistics, String> statisticsMap) {
        StringJoiner csvLine = new StringJoiner(",");

        int skip = 0;
        for (Statistics stat : Statistics.values()) {
            if(skip > 1){
                csvLine.add(statisticsMap.get(stat));
            }else {
                skip++;
            }
        }
        return csvLine.toString();
    }
}

