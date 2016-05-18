import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Palindrome {

    // O(n/2) time, O(1) space
    public static boolean isTextPalindrome(String text) {

        if (text.isEmpty() || text.length() < 3 || text == null) return false;

        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left++) != text.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPhrasePalindrome(String text) {
        return isTextPalindrome(text.replaceAll("[^a-zA-Z]", "").toLowerCase());
    }

    public static class UnitTest {

        @Test
        public void test_isPalindrome() {
            assertThat(isTextPalindrome("racecar"), is(true));
            assertThat(isTextPalindrome("raccar"), is(true));
            assertThat(isTextPalindrome("raccaa"), is(false));
            assertThat(isTextPalindrome("a"), is(false));
            assertThat(isTextPalindrome("aa"), is(false));
            assertThat(isTextPalindrome("bb"), is(false));
        }

        @Test
        public void test_isPhrasePalindrome() {
            assertThat(isPhrasePalindrome("I like dogs sgod ekil I"), is(true));
            assertThat(isPhrasePalindrome("I like dogs, SGOD ekil I"), is(true));
            assertThat(isPhrasePalindrome("I like dogs I like dogs"), is(false));
        }

    }
}
