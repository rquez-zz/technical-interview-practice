import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by ricardo on 10/25/15.
 */
public class ArrayProblems {
    // Given an image represented by a NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate
    // the image by 90 degrees. Can you do this in place?
    // O(n^2) time, O(n) space
    public static int[][] rotateMatrix(int[][] matrix, boolean rotateRight) {

        int row, col;
        int[][] rotatedMatrix = new int[matrix.length][matrix.length];

        if(rotateRight) {
            row = matrix.length - 1;
            col = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    rotatedMatrix[i][j] = matrix[row--][col];
                }
                row = matrix.length - 1;
                col++;
            }
        } else {
            row = 0;
            col = matrix.length - 1;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    rotatedMatrix[i][j] = matrix[row--][col];
                }
                col  = matrix.length - 1;
                row++;
            }
        }

        return rotatedMatrix;
    }
    // O(n^2) time, O(1) space
    public static int[][] rotateMatrixInPlace(int[][] matrix, boolean rotateRight) {

        int row, col;
        int n = matrix.length;

        if(rotateRight) {
            // Loop Each Layer
            for (int i = 0; i < n / 2; i++) {
                int first = i; // First element in layer
                int last = n - i - 1; // Last element in layer

                for (int j = first; j < last; j++) {
                    int offset = j - first;
                    int top = matrix[first][j];

                    // left -> top
                    matrix[first][j] = matrix[last - offset][first];

                    // bottom -> left
                    matrix[last - offset][first] = matrix[last][last - offset];

                    // right -> bottom
                    matrix[last][last - offset] = matrix[j][last];

                    // top -> right
                    matrix[j][last] = top;
                }
            }
        } else {
            // Loop Each Layer
            for (int i = 0; i < n / 2; i++) {
                int first = i; // First element in layer
                int last = n - i - 1; // Last element in layer

                for (int j = first; j < last; j++) {
                    int offset = j - first;
                    int left = matrix[last - offset][first];

                    // top -> left
                    matrix[last - offset][first] = matrix[first][j];

                    // right -> top
                    matrix[first][j] = matrix[j][last];

                    // bottom -> right
                    matrix[j][last] = matrix[last][last - offset];

                    // left -> bottom
                    matrix[last][last - offset] = left;
                }
            }
        }

        return matrix;
    }
    //Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0
    // O(mn + kn) time, O(1) space
    public static int[][] zeroOutMatrix(int[][] matrix) {
        ArrayList<Integer[]> list = new ArrayList();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    list.add(new Integer[]{i, j});
                }
            }
        }
        for (Integer[] pair : list) {
            int row = pair[0];
            int col = pair[1];
            int size = Math.max(matrix[row].length, matrix.length);
            for (int i = 0; i < size; i++) {
                if (matrix[row].length > i)
                    matrix[row][i] = 0;
                if (matrix.length > i)
                    matrix[i][col] = 0;
            }
        }
        return matrix;
    }
    // O(mn) time, O(n+m) space
    public static int[][] zeroOutMatrixFast(int[][] matrix) {
        boolean[] isRowZeroed = new boolean[matrix.length];
        boolean[] isColZeroed = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            if (isRowZeroed[i]) {
                continue;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (isRowZeroed[i]) {
                    break;
                }
                if (isColZeroed[j]) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    isRowZeroed[i] = true;
                    isColZeroed[j] = true;
                    int size = Math.max(matrix[i].length, matrix.length);
                    for (int k = 0; k < size; k++) {
                        if (matrix[i].length > k)
                            matrix[i][k] = 0;
                        if (matrix.length > k)
                            matrix[k][j] = 0;
                    }
                }
            }
        }
        return matrix;
    }
    public static class UnitTest {

        @Test
        public void testRotateMatrix() {
            int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
            int[][] matrixRotatedRight = new int[][]{{7,4,1},{8,5,2},{9,6,3}};
            int[][] matrixRotatedLeft = new int[][]{{3,6,9},{2,5,8},{1,4,7}};
            assertArrayEquals(matrixRotatedRight, rotateMatrix(matrix, true));
            assertArrayEquals(matrixRotatedRight, rotateMatrixInPlace(matrix, true));
            matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
            assertArrayEquals(matrixRotatedLeft, rotateMatrixInPlace(matrix, false));
        }

        @Test
        public void testZeroOutMatrix() {
            int[][] matrix = new int[][]{{1,2,3},{4,0,6},{7,8,9}};
            int[][] matrixZeroed = new int[][]{{1,0,3},{0,0,0},{7,0,9}};
            assertArrayEquals(matrixZeroed, zeroOutMatrix(matrix));
            matrix = new int[][]{{1,2,3},{4,0,6},{0,8,9}};
            matrixZeroed = new int[][]{{0,0,3},{0,0,0},{0,0,0}};
            assertArrayEquals(matrixZeroed, zeroOutMatrix(matrix));
            matrix = new int[][]{{1,2,3},{4,0,6},{0,8,9}};
            matrixZeroed = new int[][]{{0,0,3},{0,0,0},{0,0,0}};
            assertArrayEquals(matrixZeroed, zeroOutMatrixFast(matrix));
            matrix = new int[][]{{1,0,3,4,5}, {6,7,0,9,10}, {11,12,0,14,15}};
            matrixZeroed = new int[][]{{0,0,0,0,0}, {0,0,0,0,0}, {11,0,0,14,15}};
            assertArrayEquals(matrixZeroed, zeroOutMatrixFast(matrix));
        }
    }
}
