import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/*
    Given a list of sentences, decide whether or not Anya likes each sentence.
    Anya will like the sentence if there are 2 or more words of three or more letters in it that
    are palindromes or if the entire sentence is a palindrome.
    When determining whether or not a sentence is a palindrome, all non-letter characters are ignored.
    (Thus, “madam im adam” is a palindrome.)
 */
public class Anya {

    public static boolean solve(String text) {

        if (Palindrome.isPhrasePalindrome(text)) {
            return true;
        } else {
            int count = 0;
            for (String s : text.split(" ")) {
                if (Palindrome.isTextPalindrome(s))
                    count++;
                if (count >= 2)
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCases; i++) {
            String text = scanner.nextLine();
            if (solve(text)) {
                System.out.println("Ay");
            } else {
                System.out.println("Nap");
            }
        }

        scanner.close();
    }

    public static class UnitTest {

        @Test
        public void test_solve() throws Exception {
            boolean result = Tester.test(Anya.class, "in\\Anya.in", "out\\Anya.out");
            assertThat(result, is(true));
        }
    }
}
