import org.junit.Assert;
import org.junit.Test;

public class MergeSort {
    public static int[] sort(int[] array) {

        if (array.length == 1) { return array; }

        int halfIndex = array.length / 2;
        int[] a = new int[halfIndex];
        int i = 0;
        while( i < a.length) {
            a[i] = array[i];
            i++;
        }

        int[] b = new int[array.length - halfIndex];
        int j = 0;
        while (j < b.length) {
            b[j] = array[i];
            j++;
            i++;
        }

        System.out.println("NEW STACKFRAME");
        return merge(sort(a), sort(b));
    }

    // O(a.length + b.length)
    public static int[] merge(int[] a, int[] b) {

        int[] m = new int[a.length + b.length];

        int i = 0, j = 0, k = 0;
        while (k < m.length) {
            if (i >= a.length) {
                m[k] = b[j];
                j++;
            } else if (j >= b.length) {
                m[k] = a[i];
                i++;
            } else if (a[i] < b[j]) {
                m[k] = a[i];
                i++;
            } else {
                m[k] = b[j];
                j++;
            }
            k++;
        }
        return m;
    }
    public static class UnitTest {
        @Test
        public void testSort() {
            Assert.assertArrayEquals(new int[]{1,2,3,4}, sort(new int[]{2,4,1,3}));
            /*
            assertArrayEquals(new int[]{1,2,3,4,5}, sort(new int[]{2,4,5,1,3}));
            assertArrayEquals(new int[]{1,1,1,1,5}, sort(new int[]{5,1,1,1,1}));
            assertArrayEquals(new int[]{1}, sort(new int[]{1}));
            assertArrayEquals(new int[]{1,2}, sort(new int[]{2,1}));
            */
        }
    }
}
