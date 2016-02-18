public class MCSS {

    public static int MCSS(int [] a) {
        int max = 0, sum = 0, start = 0, end = 0, i=0;

        // Cycle through all possible end indexes.
        for (int j = 0; j < a.length; j++) {

            sum += a[j]; // No need to re-add all values.
            if (sum > max) {
                max = sum;
                start = i; // Although method doesn't return these
                end = j; // they can be computed.
            }
            else if (sum < 0) {
                i = j+1; // Only possible MCSSs start with an index >j.
                sum = 0; // Reset running sum.
            }
        }
        return max;
    }
}
