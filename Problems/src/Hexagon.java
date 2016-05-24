import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Problem description
 * https://www.dropbox.com/s/ihs1fyy7mmzab0e/Hexagon.pdf?dl=0
 */
public class Hexagon {

    final private static int NUM_PIECES = 7;
    final private static int NUM_SIDES = 6;

    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 1; i <= numCases; i++) {

            // Read in the Hexagon Pieces in Order
            Integer[][] puzzle = new Integer[NUM_PIECES][NUM_SIDES];
            for (int j = 0; j < NUM_PIECES; j++)
                for (int k = 0; k < NUM_SIDES; k++)
                    puzzle[j][k] = scanner.nextInt();

            // Solve the Puzzle
            Integer[] permutation = new Integer[NUM_PIECES];
            boolean[] used = new boolean[NUM_PIECES];
            String answer = stringify(solve(puzzle, permutation, used, 0));

            // Output Answer
            if (answer == null)
                System.out.format("Case %d: No solution\n", i);
            else {
                System.out.format("Case %d: %s\n", i, answer);
            }

        }
    }

    private static String stringify(Integer[] solve) {
        if (solve == null) return null;
        return Arrays.stream(solve).map(i -> i.toString()).collect(Collectors.joining(" "));
    }

    private static Integer[] solve(Integer[][] puzzle, Integer[] permutation, boolean[] used, int i) {

        // An arrangement has been made, evaluate it
        if (i == NUM_PIECES) {
            Integer[][] puzzleCopy = new Integer[NUM_PIECES][];
            for (int j = 0; j < NUM_PIECES; j++) {
                puzzleCopy[j] = puzzle[permutation[j]];
            }
            if (evaluate(puzzleCopy)) {
                return permutation;
            } else {
                return null;
            }
        }

        // Permute an arrangement of pieces
        for (int j = 0; j < NUM_PIECES; j++) {
            if (!used[j]) {
                used[j] = true;
                permutation[i] = j;

                Integer[] temp = solve(puzzle, permutation, used, i+1);
                if (temp != null) return temp;

                used[j] = false;
            }
        }

        // None of the permutations worked
        return null;
    }

    private static boolean evaluate(Integer[][] puzzle) {

        // Fix middle piece, which is at 0
        rotate(puzzle[0], 0, 1);

        // Now, fix the rest of the pieces, according to the middle piece only.
        for (int i = 1; i < NUM_PIECES; i++)
            rotate(puzzle[i], (i+2) % NUM_SIDES, puzzle[0][i-1]);

        for (int i = 1; i < NUM_PIECES; i++) {

            // Set piece to compare piece i.
            int next = i+1;

            // Make sure to compare piece 6 with piece 1
            if (next >= NUM_PIECES) next = 1;

            // Check sides that are supposed to match...
            if (!puzzle[i][(i+1) % NUM_SIDES].equals(puzzle[next][(i+4) % NUM_SIDES]))
                return false;
        }

        return true;
    }

    private static void rotate(Integer[] piece, int position, int number) {
        // Rotate the piece until the number is at the position
        while (piece[position] != number) {
            // Save the value at the start
            int temp = piece[0];
            // Shift everything to the left
            for (int i = 1; i < NUM_SIDES; i++)
                piece[i-1] = piece[i];
            // Place the start value at the end
            piece[NUM_SIDES-1] = temp;
        }
    }

    public static class UnitTest {

        @Test
        public void test_solve() throws Exception {
            boolean result = Tester.test(Hexagon.class, "in\\Hexagon.in", "out\\Hexagon.out");
            assertThat(result, is(true));
        }
    }
}
