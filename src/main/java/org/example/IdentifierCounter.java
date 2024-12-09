package org.example;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class IdentifierCounter {
    public static void main(String[] args) {
        // Prompt user to provide the source file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Java source file path: ");
        String filePath = scanner.nextLine();

        // Initialize maps
        Map<String, Integer> identifierCountMap = new TreeMap<>(); // Sorted by identifier
        Map<String, List<String>> identifierLinesMap = new TreeMap<>();

        // Read and process the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                // Use a regex to match potential identifiers
                String[] tokens = line.split("[^A-Za-z0-9_]+"); // Split using non-identifier characters
                for (String token : tokens) {
                    if (!token.isEmpty()) { // Skip empty tokens
                        // Update identifier count
                        identifierCountMap.put(token, identifierCountMap.getOrDefault(token, 0) + 1);

                        // Add line information for the identifier
                        String lineWithNumber = lineNumber + ". " + line.trim();
                        identifierLinesMap
                                .computeIfAbsent(token, k -> new ArrayList<>())
                                .add(lineWithNumber);
                    }
                }
            }

            // Print the result
            System.out.println("Identifiers and their occurrences:");
            for (String identifier : identifierCountMap.keySet()) {
                System.out.println(identifier + " " + identifierCountMap.get(identifier) + " " + identifierLinesMap.get(identifier));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}

