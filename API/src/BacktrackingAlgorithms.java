import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Practice on BacktrackingAlgorithms
 */
public class BacktrackingAlgorithms {

    /**
     * Permutate k integers in array
     *
     * O(n!) time, O(n) space
     * @param array
     * @param k
     * @return
     */
    public static void permutate(int[] array, int k) {

        if (k == array.length) {
            for (int i : array)
                System.out.print(i + " ");
            System.out.println();
        }

        for (int i = k; i < array.length; i++) {
            swap(array, i, k);
            permutate(array, k+1);
            swap(array, k, i);
        }
    }

    private static void swap(int[] array, int i, int k) {
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }


    /**
     * Permutate k characters in string
     *
     * O(n!) time, O(n) space
     * @param string
     * @param k
     * @return
     */
    public static void permutate(char[] string, int k) {

        if (k == string.length) {
            for (char i : string)
                System.out.print(i + " ");
            System.out.println();
        }

        for (int i = k; i < string.length; i++) {
            swap(string, i, k);
            permutate(string, k+1);
            swap(string, k, i);
        }
    }

    public static void permutate(String string, int k) {
        permutate(string.toCharArray(), k);
    }

    /**
     * Using a boolean array to decide if an element should be included in the subset
     * @param p
     * @param in
     * @param set
     */
    public static void subsets(int p, boolean[] in, int[] set) {
        if (p == set.length) {
            for (int i = 0; i < set.length; i++) {
                if (in[i])
                    System.out.print(set[i] + " ");
            }
            System.out.println();
            return;
        }

        // L[p] is not in the subset
        in[p] = false;
        subsets(p+1, in, set);
        // L[p] is in the subset
        in[p] = true;
        subsets(p+1, in, set);
    }

    /**
     * Loops through each bit in the mask up to set.length positions
     * @param set
     */
    public static void subsets(int[] set) {
        for (int mask = 0; mask < (1 << set.length); mask++) {
            for (int k = 0; k < set.length; k++) {
                if ((mask & (1<<k)) > 0) {
                    System.out.print(set[k] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Builds combinations with increasing size up to k
     * @param p, the index for the set
     * @param k, the combination size
     * @param size, the growing size of the subset up to k
     * @param combo, the array holding the building combination
     * @param set
     */
    public static void combinations(int p, int k, int size, int[] combo, int[] set) {

        // This stops p from going beyond the set length, which causes stackoverflow
        if (set.length - p < k - size) return;
        if (size == k) {

            for (int i : combo) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        // Skip set[p]
        combinations(p+1, k, size, combo, set);
        // Use set[p] in the combo
        combo[size] = set[p];
        combinations(p + 1, k, size + 1, combo, set);

    }

    /**
     * Builds the combinations using a bit mask.
     * The combination is only used when the bit mask has set.length 1 bits so
     * I have to count the bits first and then print that combination.
     * @param k
     * @param set
     */
    public static void combinations(int k, int[] set) {
        for (int mask = 0; mask < (1 << set.length); mask++) {

            int temp = mask;
            int count = 0;
            while (temp != 0) {
                count += temp & 1;
                temp >>= 1;
            }

            if (k == count){
                for (int i = 0; i < set.length; i++) {
                    if ((mask & (1 << i)) > 0) {
                        System.out.print(set[i] + " ");
                    }
                }
            System.out.println();
            }
        }
    }

    /**
     * Calculates the gcd of a and b using euclidean algorithm
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static void swap(char[] array, int i, int k) {
        char temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }

    public static class BacktrackingAlgorithmsTest {

        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream systemOut = System.out;

        @Before
        public void setUp() {
            System.setOut(new PrintStream(outContent));
        }

        @Test
        public void testPermutateIntArray() {

            int length = 3;

            int[] array = new int[length];
            for (int i = 0; i < length; i++)
                array[i] = i + 1;

            BacktrackingAlgorithms.permutate(array, 0);
            String[] permutations = outContent.toString().split(" \n");

            assertEquals(factorial(length), permutations.length);

            System.setOut(systemOut);
            for (String s : permutations) {
                System.out.println(s);
            }
        }

        @Test
        public void testPermutateString() {

            int length = 4;

            char[] array = new char[length];
            for (int i = 0; i < length; i++)
                array[i] = (char)(i + 97);

            BacktrackingAlgorithms.permutate(array, 0);

            String[] permutations = outContent.toString().split(" \n");

            assertEquals(factorial(length), permutations.length);

        }

        @Test
        public void testSubsetsBoolean() {

            int length = 3;
            Random rand = new Random();
            int[] array = new int[length];
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(11);
            }

            BacktrackingAlgorithms.subsets(0, new boolean[length], array);
            String[] subsets = outContent.toString().split(" \n");
            assertEquals(subsets.length, (int)Math.pow(2, length) - 1);
        }

        @Test
        public void testSubsetsBitmasking() {

            int length = 3;
            Random rand = new Random();
            int[] array = new int[length];
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(11);
            }

            BacktrackingAlgorithms.subsets(array);
            String[] subsets = outContent.toString().split(" \n");
            assertEquals(subsets.length, (int)Math.pow(2, length) - 1);
            printOutSet(subsets, array);
        }

        @Test
        public void testCombinations() {

            int length = 4;
            Random rand = new Random();
            int[] array = new int[length];
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(11);
            }

            BacktrackingAlgorithms.combinations(0, 2, 0, new int[2], array);
            String[] combos = outContent.toString().split(" \n");
            assertEquals(combos.length, combos(4,2));
            printOutSet(combos,array);
        }

        @Test
        public void testCombinationsBit() {
            int n = 4;
            int k = 2;

            Random rand = new Random();
            int[] array = new int[n];
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(11);
            }

            BacktrackingAlgorithms.combinations(k,array);
            String[] combos = outContent.toString().split(" \n");
            assertEquals(combos.length, combos(n,k));
            printOutSet(combos,array);
        }

        private int factorial(int num) {
            if (num == 1) return 1;
            return num * factorial(num - 1);
        }

        private int combos(int num, int k) {
            return factorial(num) / (factorial(k) * factorial(num - k));
        }

        private void printOutSet(String[] set, int[] array) {
            System.setOut(systemOut);
            for (int i : array) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (String i : set) {
                System.out.println(i);
            }
        }
    }
}
