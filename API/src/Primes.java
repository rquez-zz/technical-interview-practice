import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {
    public static ArrayList<Pair<Integer, Integer>> primeFactorize(int n) {
        ArrayList<Pair<Integer, Integer>> res = new ArrayList();
        int div = 2;
        while (div*div <= n) {
            int exp = 0;
            while (n%div == 0) {
                n /= div;
                exp++;
            }

            // Add the prime factor
            if (exp > 0)
                res.add(new Pair(div, exp));
            div++;
        }

        // Add the left over prime
        if (n > 1)
            res.add(new Pair(n, 1));

        return res;
    }

    public static boolean[] primeSieve(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0]= false;
        isPrime[1] = false;

        for (int i=2; i*i<=n; i++)
            for (int j=2*i; j<=n; j+=i)
                isPrime[j] = false;
        return isPrime;
    }

    public static class PrimesTest {

        @Test
        public void test_primeFactorize() {

            List<Pair<Integer, Integer>> list = primeFactorize(22);
            for (Pair<Integer, Integer> pair : list) {
                System.out.println(pair.getKey() + " " + pair.getValue());
            }
        }

        @Test
        public void test_primeSieve() {

            boolean[] list = primeSieve(22);
            for (int b = 0; b < list.length; b++) {
                System.out.println(b + " " + list[b]);
            }
        }
    }
}
