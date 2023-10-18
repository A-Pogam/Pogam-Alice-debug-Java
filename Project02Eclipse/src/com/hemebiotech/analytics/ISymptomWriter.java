package com.hemebiotech.analytics;

import java.util.Map;

/**
 * Anything that will read symptom data from a source
 */

public interface ISymptomWriter {
    /**
     * Write the symptoms and their counts.
     *
     * @param symptoms A map that associates symptoms with their counts.
     */
    void writeSymptoms(Map<String, Integer> symptoms);
}