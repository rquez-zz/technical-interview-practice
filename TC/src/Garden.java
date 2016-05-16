import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 	We have a garden that contains some flowers.
 * 	The flowers are arranged into a rectangle with 2 rows and n columns.
 * 	You are given a String[] garden that describes our garden.
 * 	The String[] garden has two elements, each with n characters.
 * 	Each character in garden represents one flower.
 * 	Different characters in garden represent different colors.
 * 	Two flowers are considered adjacent if they are in the same column, or if they are next to each other in the same row.
 * 	Your goal is to produce a garden in which no pair of adjacent flowers shares the same color.
 * 	You may rearrange the flowers in your garden arbitrarily (without changing its shape).
 * 	More precisely, you may swap any two flowers in your garden, and you may do so arbitrarily many times.
 * 	If the goal can be reached, return a String[] that will encode the resulting garden in the same format as was used for garden.
 * 	If there are multiple solutions, you may return any of them.
 * 	If there is no solution, return an empty String[].
 */
public class Garden {
    public static String[] solve(String[] garden) {

        int n = garden[0].length();

        HashMap<Character, Integer> frequencyTable = new HashMap();

        // Count frequencies
        for (String s : garden) {
            for (Character c : s.toCharArray()) {
               if (frequencyTable.containsKey(c)) {
                   frequencyTable.put(c, frequencyTable.get(c) + 1);
               } else {
                   frequencyTable.put(c, 1);
               }
            }
        }

        char[] newGarden = new char[2*n];

        int firstEmptyPlace = 0;
        for (Character c : frequencyTable.keySet()) {
            boolean firstEmptyPlaceFound = false;
            int index = firstEmptyPlace;
            for (int i = 0; i < frequencyTable.get(c); i++) {
                if (index >= newGarden.length) {
                    return new String[]{};
                }
                newGarden[index] = c;
                if (index < newGarden.length - 1 && newGarden[index+1] == '\0' && !firstEmptyPlaceFound) {
                    firstEmptyPlace = index+1;
                    firstEmptyPlaceFound = true;
                }
                index += 2;
            }
        }

        String first = new String();
        String second = new String();
        for (Character c : newGarden) {
            if (first.length() == n) {
                second += c;
            } else {
                first += c;
            }
        }

        int count = 0;
        for (Character c : frequencyTable.keySet()) {
            if (count != 0 && count != frequencyTable.get(c)) {
                count = frequencyTable.get(c);
            } else {
                second = new StringBuilder(second).reverse().toString();
                break;
            }
        }

        return new String[]{first, second};
    }

    public static class UnitTests {

        @Test
        public void testSolve() {

            assertThat(solve(new String[]{"aa", "bb"}), is(new String[]{"ab", "ba"}));
            assertThat(solve(new String[]{"abcd", "abcd"}), is(new String[]{"abab", "dcdc"}));
            assertThat(solve(new String[]{"aaa", "aab"}), is(new String[]{}));
            assertThat(solve(new String[]{"abcdefghijklm", "nopqrstuvwxyz"}), is(new String[]{"abcdefghijklm", "zyxwvutsrqpon"}));
        }

    }
}
