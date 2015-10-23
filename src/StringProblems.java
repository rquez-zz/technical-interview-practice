import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 10/21/15.
 */
public class StringProblems {

    // Implement an algorithm to determine if a string has all unique characters.
    // What if you cannot use additional data structures?
    // Lets assume the string has only ASCII characters.
    // O(n) time, O(1) space
    public static boolean hasUniqueCharacters(String s) {

        if (s.length() > 128) { return false; }

        // boolean are initialized to false by default
        boolean[] asciiTable = new boolean[128];

        for (int i = 0; i < s.length(); i++) {
            if (asciiTable[s.charAt(i)]) {
                return false;
            } else {
                asciiTable[s.charAt(i)] = true;
            }
        }
        return true;
    }
    public static boolean hasUniqueCharactersAZ(String s) {

        int checker = 0;

        for (int i = 0; i < s.length(); i++) {

            int value = s.charAt(i) - 'a';
            int bitShift = 1 << value;
            if ((checker & bitShift) > 0) {
               return false;
            }

            checker = checker | bitShift;
        }

        return true;
    }

    // Given two strings, write a method to decide if one is a permutation of the other.
    // O(n^2) time, O(1) space
    public static boolean isPermutation(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        for (int i = 0; i < a.length(); i++) {
            if(b.indexOf(a.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
    // O(2n) time, O(n) space
    public static boolean isPermutationHash(String a, String b) {
        if (a.length() != b.length()) { return false; }
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            if (!hashSet.contains(a.charAt(i))) {
                hashSet.add(a.charAt(i));
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (!hashSet.contains(b.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    // O(n) time, O(1) space, only a-z
    public static boolean isPermutationBitShift(String a, String b) {
        if (a.length() != b.length()) { return false; }
        int aVal = 0, bVal = 0;
        for (int i = 0; i < a.length(); i++) {
            aVal |= 1 << (a.charAt(i) - 'a');
            bVal |= 1 << (b.charAt(i) - 'a');
        }
        if (aVal == bVal) { return true; }
        else { return false; }
    }
    // O(2n) time, O(1) space, assume 256 letters
    public static boolean isPermutationFrequencyTable(String a, String b) {
        if (a.length() != b.length()) { return false; }

        int[] letters = new int[256];
        for (int i = 0; i < a.length(); i++) {
            letters[a.charAt(i)]++;
        }
        for (int i = 0; i < b.length(); i++) {
            if (--letters[b.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }
    public static class UnitTest {
        @Test
        public void testHasUniqueCharacters() {
            assertTrue(!hasUniqueCharacters("hello"));
            assertTrue(hasUniqueCharacters("helo"));
            assertTrue(!hasUniqueCharacters(new String(new char[129])));
            assertTrue(hasUniqueCharactersAZ("helo"));
            assertTrue(!hasUniqueCharactersAZ("hello"));


        }

        @Test
        public void testIsPermutation() {
            assertTrue(isPermutation("hello", "elloh"));
            assertTrue(!isPermutation("hellt", "elloh"));
            assertTrue(!isPermutation("hllo", "elloh"));
            assertTrue(isPermutationHash("hello", "elloh"));
            assertTrue(!isPermutationHash("hellt", "elloh"));
            assertTrue(!isPermutationHash("hllo", "elloh"));
            assertTrue(isPermutationBitShift("hello", "elloh"));
            assertTrue(!isPermutationBitShift("hellt", "elloh"));
            assertTrue(!isPermutationBitShift("hllo", "elloh"));
            assertTrue(isPermutationBitShift("lobster", "lobster"));
            assertTrue(!isPermutationBitShift("lobstxr", "lobster"));
            assertTrue(isPermutationFrequencyTable("hello", "elloh"));
            assertTrue(!isPermutationFrequencyTable("hellt", "elloh"));
            assertTrue(!isPermutationFrequencyTable("hllo", "elloh"));
        }
    }
}
