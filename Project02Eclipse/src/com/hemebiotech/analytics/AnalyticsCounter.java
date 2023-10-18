package com.hemebiotech.analytics;

import java.io.BufferedReader; //library which gives functionalities to work with files
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // library  provides us with useful data structures like Maps.
import java.util.HashMap;
import java.util.Map;

public class AnalyticsCounter { // erased
	public static void main(String args) { // main function / erased throws exeption bc dealed with in/outputFileName
		String inputFileName = "symptoms.txt"; // read the symptoms
		String outputFileName = "result.out"; // save results

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
				FileWriter writer = new FileWriter(outputFileName)) { // changed symptoms.txt by inputFileName &
																		// try-with-resources close ressources to avoid
																		// saturation and disponibility

			Map<String, Integer> symptomCounts = new HashMap<>(); // using map to stock & way to associate symptoms with
																	// occurency
			String line; // temporary content while reading inputFileName

			while ((line = reader.readLine()) != null) { // loop that reads the lines of the symptoms file one by one
															// until there are no more lines to read
				if (!line.isEmpty()) { // way to count occurency of symptoms
					symptomCounts.put(line, symptomCounts.getOrDefault(line, 0) + 1); // if 0 ocurency, add 1, if
																						// already there, add 1
				}
			}

			for (Map.Entry<String, Integer> entry : symptomCounts.entrySet()) { // way to avoid writing code for each
																				// symptoms
				writer.write(entry.getKey() + ": " + entry.getValue() + "\n"); // write results of symptoms/occurency
																				// report
			}

			System.out.println("Report finished and wrote in" + outputFileName); // write it in
																					// outputFileName/result.out
		} catch (IOException e) { // way to detect errors
			e.printStackTrace(); // print the errors
		}
	}
}

/**
 * package com.hemebiotech.analytics;
 * 
 * import java.util.List;
 * import java.util.Map;
 * 
 * public class AnalyticsCounter { // erased
 * public static void main(String args) { // main function / erased throws
 * exeption bc dealed with in/outputFileName
 * String inputFileName = "symptoms.txt"; // read the symptoms
 * String outputFileName = "result.out"; // save results
 * 
 * // Lire les symptômes à partir du fichier
 * ISymptomReader symptomReader = new ReadSymptomDataFromFile(inputFileName);
 * List<String> symptoms = symptomReader.GetSymptoms();
 * 
 * // Compter les symptômes et stocker les résultats dans une Map
 * Map<String, Integer> symptomCounts = SymptomCounter.countSymptoms(symptoms);
 * 
 * // Créer une instance de WriteSymptomDataToFile pour écrire les résultats
 * dans
 * // "result.out"
 * WriteSymptomDataToFile writer = new WriteSymptomDataToFile(outputFileName);
 * 
 * // Écrire les résultats dans le fichier de sortie
 * writer.writeSymptoms(symptomCounts);
 * 
 * System.out.println("Report finished and wrote in " + outputFileName);
 * }
 * }
 */