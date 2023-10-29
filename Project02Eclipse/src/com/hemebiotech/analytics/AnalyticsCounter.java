package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * AnalyticsCounter class for processing and counting symptoms.
 */

public class AnalyticsCounter {
	private ISymptomReader reader;
	private ISymptomWriter writer;

	/**
	 * Constructor for initializing the reader and writer.
	 *
	 * @param reader The ISymptomReader instance for reading symptoms.
	 * @param writer The ISymptomWriter instance for writing symptoms.
	 */
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	/**
	 * Get the list of symptoms from the data source.
	 *
	 * @return A list of symptoms as strings.
	 */
	public List<String> getSymptoms() {
		return reader.GetSymptoms();
	}

	/**
	 * Count the occurrences of each symptom.
	 *
	 * @param symptoms A list of symptoms to count.
	 * @return A map containing symptoms as keys and their counts as values.
	 */
	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomCounts = new HashMap<>();
		for (String symptom : symptoms) {
			if (!symptom.isEmpty()) {
				symptomCounts.put(symptom, symptomCounts.getOrDefault(symptom, 0) + 1);
			}
		}
		return symptomCounts;
	}

	/**
	 * Sort the symptoms alphabetically.
	 *
	 * @param symptoms A map of symptoms and their counts.
	 * @return A sorted map of symptoms.
	 */
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		Map<String, Integer> sortedSymptoms = new TreeMap<>(symptoms);
		return sortedSymptoms;
	}

	/**
	 * Write the symptoms and their counts to the output file.
	 *
	 * @param symptoms A map of symptoms and their counts.
	 */
	public void writeSymptoms(Map<String, Integer> symptoms) {
		writer.writeSymptoms(symptoms);
	}

	/**
	 * Generate the symptom report by orchestrating the steps.
	 */
	public void generateReport() {
		List<String> symptoms = getSymptoms();
		Map<String, Integer> symptomCounts = countSymptoms(symptoms);
		Map<String, Integer> sortedSymptoms = sortSymptoms(symptomCounts);
		writeSymptoms(sortedSymptoms);
		System.out.println("Report generated and written to the output file.");
	}

	/**
	 * The entry point of the application.
	 *
	 * @param args Command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {
		String inputFileName = "../symptoms.txt";
		String outputFileName = "result.out";

		// Create an instance of AnalyticsCounter with the appropriate reader and writer
		AnalyticsCounter counter = new AnalyticsCounter(new ReadSymptomDataFromFile(inputFileName),
				new WriteSymptomDataToFile(outputFileName));
		counter.generateReport();
	}
}