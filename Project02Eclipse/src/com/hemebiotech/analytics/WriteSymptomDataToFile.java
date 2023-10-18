package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Writes symptom data to a file.
 */

public class WriteSymptomDataToFile implements ISymptomWriter {

    private String filePath;

    /**
     * Constructor.
     *
     * @param filePath The path to the output file where symptoms will be written.
     */

    public WriteSymptomDataToFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        try {
            // Open the output file for writing
            FileWriter writer = new FileWriter(filePath);

            // Loop through the symptom data provided as a map
            for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                String symptom = entry.getKey(); // // Get the symptom name
                int quantity = entry.getValue(); // Get the quantity of occurrences
                writer.write(symptom + ": " + quantity + "\n"); // Write the symptom and its count to the file
            }
            // Close the output file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
