package com.etteplan.servicemanual.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CSVReader {

    private static List<String> valueNames;
    private static List<Map<String, String>> values;
    private static String errorDescription;

    public static boolean getValuelistFromCSVFile(String filename) {

        // Read CSV file
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            // If file is not found report that
            setErrorDescription("File not found: " + filename);
            setValueNames(List.of());
            setValues(List.of(Map.of()));
            return false;
        }

        setErrorDescription("");
        scanner.useDelimiter(",");

        // Read csv header
        List<String> header = Arrays.asList(scanner.nextLine().split(","));
        setValueNames(header);

        List<Map<String, String>> lines = new ArrayList<>();
        // Read through the file.
        while (scanner.hasNext()) {

            Map<String, String> mapline = new HashMap<String, String>();// store values here

            AtomicInteger index = new AtomicInteger();// index is used in forEach loop

            Arrays.stream(scanner.nextLine().split(","))// Read CSV line as a stream and split values with comma
                    .forEach(i -> mapline.put(header.get(index.getAndIncrement()), i));// Index is needed to get next
                                                                                       // valuename from header list

            lines.add(mapline); // Add readed line to list

        }

        scanner.close();

        setValues(lines);

        return true;
    }

    public static List<String> getValueNames() {
        return valueNames;
    }

    public static List<Map<String, String>> getValues() {
        return values;
    }

    public static String getError() {
        return errorDescription;
    }

    private static void setValueNames(List<String> names) {
        valueNames = names;
    }

    private static void setValues(List<Map<String, String>> lines) {
        values = lines;
    }

    private static void setErrorDescription(String desc) {
        errorDescription = desc;
    }
}
