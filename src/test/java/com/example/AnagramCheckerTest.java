package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnagramCheckerTest {

    @Test
    public void testBasicAnagrams() {
        assertTrue(AnagramChecker.areAnagrams("listen", "silent"));
        assertTrue(AnagramChecker.areAnagrams("python", "typhon"));
        assertTrue(AnagramChecker.areAnagrams("abc", "bca"));
    }

    @Test
    public void testNotAnagrams() {
        assertFalse(AnagramChecker.areAnagrams("hello", "world"));
        assertFalse(AnagramChecker.areAnagrams("java", "python"));
        assertFalse(AnagramChecker.areAnagrams("abc", "def"));
    }

    @Test
    public void testCaseInsensitivity() {
        assertTrue(AnagramChecker.areAnagrams("Listen", "Silent"));
        assertTrue(AnagramChecker.areAnagrams("PYTHON", "typhon"));
        assertTrue(AnagramChecker.areAnagrams("AbC", "bCa"));
    }

    @Test
    public void testWithSpaces() {
        assertTrue(AnagramChecker.areAnagrams("The Eyes", "They See"));
        assertTrue(AnagramChecker.areAnagrams("a gentleman", "elegant man"));
        assertTrue(AnagramChecker.areAnagrams("dormitory", "dirty room"));
    }

    @Test
    public void testEmptyStrings() {
        assertTrue(AnagramChecker.areAnagrams("", ""));
    }

    @Test
    public void testSingleCharacters() {
        assertTrue(AnagramChecker.areAnagrams("a", "a"));
        assertFalse(AnagramChecker.areAnagrams("a", "b"));
    }

    @Test
    public void testDifferentLengths() {
        assertFalse(AnagramChecker.areAnagrams("cat", "cats"));
        assertFalse(AnagramChecker.areAnagrams("abc", "abcd"));
    }

    @Test
    public void testSpecialCharactersIgnored() {
        assertTrue(AnagramChecker.areAnagrams("a-b-c", "c,b,a"));
        assertTrue(AnagramChecker.areAnagrams("a@b#c", "cba"));
    }

    @Test
    public void testDuplicateCharacters() {
        assertTrue(AnagramChecker.areAnagrams("aabb", "abab"));
        assertFalse(AnagramChecker.areAnagrams("aab", "abb"));
    }

    @Test
    public void testNumbersIgnored() {
        assertTrue(AnagramChecker.areAnagrams("a1b2c3", "c3b2a1"));
        assertTrue(AnagramChecker.areAnagrams("123abc", "cba123"));
    }

    @Test
    public void testNullStringThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> AnagramChecker.areAnagrams(null, "test"));
        assertThrows(IllegalArgumentException.class,
            () -> AnagramChecker.areAnagrams("test", null));
    }

    @Test
    public void testCharacterFrequency() {
        int[] freq = AnagramChecker.getCharacterFrequency("aabbcc");
        assertEquals(2, freq[0]); // 'a' count
        assertEquals(2, freq[1]); // 'b' count
        assertEquals(2, freq[2]); // 'c' count
    }

    @Test
    public void testCharacterFrequencyWithSpaces() {
        int[] freq = AnagramChecker.getCharacterFrequency("a b c");
        assertEquals(1, freq[0]); // 'a' count
        assertEquals(1, freq[1]); // 'b' count
        assertEquals(1, freq[2]); // 'c' count
    }

    @Test
    public void testCharacterFrequencyNull() {
        assertThrows(IllegalArgumentException.class,
            () -> AnagramChecker.getCharacterFrequency(null));
    }

    @Test
    public void testRealWorldExamples() {
        assertTrue(AnagramChecker.areAnagrams("astronomer", "moon starer"));
        assertTrue(AnagramChecker.areAnagrams("desperation", "a rope ends it"));
        assertTrue(AnagramChecker.areAnagrams("the morse code", "here come dots"));
    }

    @Test
    public void testLongStrings() {
        String str1 = "abcdefghijklmnopqrstuvwxyz";
        String str2 = "zyxwvutsrqponmlkjihgfedcba";
        assertTrue(AnagramChecker.areAnagrams(str1, str2));
    }
}
