import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MCSS {

    public static int MCSS(int [] a) {
        int max = 0;
        int sum = 0;
        int start = 0;
        int end = 0;
        int i = 0;

        // Cycle through all possible end indexes.
        for (int j = 0; j < a.length; j++) {

            sum += a[j]; // No need to re-add all values.
            if (sum > max) {
                max = sum;
            } else if (sum < 0) {
                i = j+1; // Only possible MCSSs start with an index greater than j
                sum = 0; // Reset running sum.
            }
        }
        return max;
    }

    public static class MCSSTest {

        @Test
        public void test_MCSS() {
            assertThat(20, is(MCSS(new int[]{5,7,-3,1,-11,8,12})));
        }
    }
}
