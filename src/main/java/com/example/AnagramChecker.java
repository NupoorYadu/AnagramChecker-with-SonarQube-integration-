package com.example;

/**
 * Anagram Checker - Checks if two strings are anagrams without sorting.
 * Uses character frequency counting approach.
 * 
 * Time Complexity: O(n + m) where n and m are string lengths
 * Space Complexity: O(1) - fixed size array for 26 letters
 */
public class AnagramChecker {

    /**
     * Checks if two strings are anagrams of each other.
     * Case-insensitive, ignores spaces.
     * 
     * @param str1 first string
     * @param str2 second string
     * @return true if strings are anagrams, false otherwise
     * @throws IllegalArgumentException if either string is null
     */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Strings cannot be null");
        }

        // Normalize strings: lowercase and remove non-alphabetic characters
        String normalized1 = str1.toLowerCase().replaceAll("[^a-z]", "");
        String normalized2 = str2.toLowerCase().replaceAll("[^a-z]", "");

        // If lengths differ, they cannot be anagrams
        if (normalized1.length() != normalized2.length()) {
            return false;
        }

        // Empty strings are considered anagrams
        if (normalized1.isEmpty()) {
            return true;
        }

        // Character frequency array for a-z (26 letters)
        int[] charCount = new int[26];

        // Count characters in first string
        for (char c : normalized1.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                charCount[c - 'a']++;
            }
        }

        // Subtract characters in second string
        for (char c : normalized2.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                charCount[c - 'a']--;
            }
        }

        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Get character frequency map of a string.
     * 
     * @param str input string
     * @return array of character frequencies for a-z
     */
    public static int[] getCharacterFrequency(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String cannot be null");
        }

        int[] frequency = new int[26];
        String normalized = str.toLowerCase().replaceAll("[^a-z]", "");

        for (char c : normalized.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                frequency[c - 'a']++;
            }
        }

        return frequency;
    }

    /**
     * Main method to demonstrate anagram checking.
     */
    public static void main(String[] args) {
        System.out.println("=== Anagram Checker Demo ===\n");

        testAnagram("listen", "silent");
        testAnagram("hello", "world");
        testAnagram("python", "typhon");
        testAnagram("The Eyes", "They See");
        testAnagram("", "");
    }

    /**
     * Helper method to test and display results.
     */
    private static void testAnagram(String str1, String str2) {
        boolean result = areAnagrams(str1, str2);
        String status = result ? "✓ ARE ANAGRAMS" : "✗ NOT ANAGRAMS";
        System.out.println("\"" + str1 + "\" and \"" + str2 + "\"");
        System.out.println("Result: " + status + "\n");
    }
}
