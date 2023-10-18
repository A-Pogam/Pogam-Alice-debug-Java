package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	private ISymptomReader reader; // Instance of ISymptomReader for reading symptoms
	private ISymptomWriter writer; // Instance of ISymptomWriter for writing symptoms

	// Constructor to initialize the reader and writer
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	// Get the list of symptoms from the data source
	public List<String> getSymptoms() {
		return reader.GetSymptoms();
	}

	// Count the occurrences of each symptom
	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomCounts = new HashMap<>(); // Create a map to store symptom counts
		for (String symptom : symptoms) { // Iterate through the list of symptoms
			if (!symptom.isEmpty()) { // Check if the symptom is not empty
				symptomCounts.put(symptom, symptomCounts.getOrDefault(symptom, 0) + 1);
				// Increment the count for the symptom or initialize it to 1
			}
		}
		return symptomCounts; // Return the map of symptom counts
	}

	// Sort the symptoms alphabetically
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		Map<String, Integer> sortedSymptoms = new TreeMap<>(symptoms); // Create a TreeMap for sorting
		return sortedSymptoms; // Return the sorted map
	}

	// Write the symptoms and their counts to the output file
	public void writeSymptoms(Map<String, Integer> symptoms) {
		writer.writeSymptoms(symptoms); // Delegate the writing to the ISymptomWriter
	}

	// Generate the symptom report by orchestrating the steps
	public void generateReport() {
		List<String> symptoms = getSymptoms(); // Get the list of symptoms
		Map<String, Integer> symptomCounts = countSymptoms(symptoms); // Count the occurrences
		Map<String, Integer> sortedSymptoms = sortSymptoms(symptomCounts); // Sort alphabetically
		writeSymptoms(sortedSymptoms); // Write the report to the output file
		System.out.println("Report generated and written to the output file.");
	}

	// The entry point of the application
	public static void main(String[] args) {
		String inputFileName = "symptoms.txt"; // Input file name
		String outputFileName = "result.out"; // Output file name

		// Create an instance of AnalyticsCounter with the appropriate reader and writer
		AnalyticsCounter counter = new AnalyticsCounter(new ReadSymptomDataFromFile(inputFileName),
				new WriteSymptomDataToFile(outputFileName));
		counter.generateReport(); // Generate and write the symptom report
	}
}
