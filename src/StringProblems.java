import org.junit.Test;

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

    public static class UnitTest {
        @Test
        public void testHasUniqueCharacters() {
            assertTrue(!hasUniqueCharacters("hello"));
            assertTrue(hasUniqueCharacters("helo"));
            assertTrue(!hasUniqueCharacters(new String(new char[129])));
            assertTrue(hasUniqueCharactersAZ("helo"));
            assertTrue(!hasUniqueCharactersAZ("hello"));
        }
    }
}
