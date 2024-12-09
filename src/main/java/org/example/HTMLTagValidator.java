package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class HTMLTagValidator {

    public static void main(String[] args) {
        // Specify the file containing the tags
        String fileName = "tags.txt";

        try (Scanner scanner = new Scanner(new File(fileName))) {
            Stack<String> tagStack = new Stack<>();

            // Read and process tags from the file
            while (scanner.hasNext()) {
                String tag = scanner.next();

                if (isOpeningTag(tag)) {
                    // Push opening tag to the stack
                    tagStack.push(tag);
                } else if (isClosingTag(tag)) {
                    if (tagStack.isEmpty()) {
                        System.out.println("Error: Unmatched closing tag " + tag);
                        return;
                    }
                    String topTag = tagStack.pop();
                    if (!isMatchingPair(topTag, tag)) {
                        System.out.println("Error: Mismatched tags " + topTag + " and " + tag);
                        return;
                    }
                } else if (!isSelfClosingTag(tag)) {
                    System.out.println("Error: Invalid tag " + tag);
                    return;
                }
            }

            // Check if there are unmatched opening tags
            if (!tagStack.isEmpty()) {
                System.out.println("Error: Unmatched opening tag(s) " + tagStack);
            } else {
                System.out.println("The tags are properly nested.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + fileName);
        }
    }

    // Check if the tag is an opening tag (e.g., <p>, <ul>)
    private static boolean isOpeningTag(String tag) {
        return tag.matches("<[a-zA-Z]+>");
    }

    // Check if the tag is a closing tag (e.g., </p>, </ul>)
    private static boolean isClosingTag(String tag) {
        return tag.matches("</[a-zA-Z]+>");
    }

    // Check if the tag is a self-closing tag (e.g., <br>)
    private static boolean isSelfClosingTag(String tag) {
        return tag.matches("<br>");
    }

    // Check if the opening tag matches the closing tag
    private static boolean isMatchingPair(String openingTag, String closingTag) {
        String openingTagName = openingTag.substring(1, openingTag.length() - 1); // Remove <>
        String closingTagName = closingTag.substring(2, closingTag.length() - 1); // Remove </ >
        return openingTagName.equals(closingTagName);
    }
}
