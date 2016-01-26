/*
 Errors:
    First of all you completely failed to realize the objective of this problem.
    You needed to rotate the matrix, not shift it.
    So you spent all this time on shifting it, not realizing the the rotation implementation
    is actually a lot easier.
    Nonetheless shifting the matrix is pretty hard.

     Given a 2-dimensional square matrix, rotate the matrix clockwise.
     Imagine concentric circles.
     Input from stdin: first line is length, subsequent lines are rows of the matrix.
     Output the matrix to stdout.
*/
public class MatrixRightShift {
    public static void main(String args[]) {
        int[][] m = new int[3][3];
        m[0] = new int[] {1, 2, 3};
        m[1] = new int[] {8, 9, 4};
        m[2] = new int[] {7, 6, 5};

        printMatrix(shiftRightMatrix(m));
        System.out.println();

        m = new int[5][5];
        m[0] = new int[] {1, 2, 3, 4, 5};
        m[1] = new int[] {6, 1, 2, 3, 6};
        m[2] = new int[] {5, 8, 0, 4, 7};
        m[3] = new int[] {4, 7, 6, 5, 8};
        m[4] = new int[] {3, 2, 1, 0, 9};

        printMatrix(shiftRightMatrix(m));
        System.out.println();

        m = new int[6][6];
        m[0] = new int[] {1, 2, 3, 4, 5, 6};
        m[1] = new int[] {0, 1, 2, 3, 4, 7};
        m[2] = new int[] {9, 2, 1, 2, 5, 8};
        m[3] = new int[] {8, 1, 4, 3, 6, 9};
        m[4] = new int[] {7, 0, 9, 8, 7, 0};
        m[5] = new int[] {6, 5, 4, 3, 2, 1};

        printMatrix(shiftRightMatrix(m));
    }

    public static void printMatrix(int[][] m) {
        for (int[] a : m) {
            for (int b: a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }
    public static int[][] shiftRightMatrix(int[][] m) {
        int length = m[0].length;
        int circles = length / 2;
        for (int x = 0; x < circles; x++) {
            int tempT = 0, tempR = 0, tempB = 0, tempL = 0;
            // TOP EDGE
            for (int y = x; y < length - 1 - x; y++) {
                if ((y-x) == 0) {
                    tempT = m[x][y + 1];
                    m[x][y + 1] = m[x][y];
                }
                else {
                    int subTemp = tempT;
                    tempT = m[x][y + 1];
                    m[x][y + 1] = subTemp;
                }
            }
            // RIGHT EDGE
            for (int y = x; y < length - 1 - x; y++) {
                if ((y-x) == 0) {
                    tempR = m[y + 1][length - 1 - x];
                    m[y + 1][length - 1 - x] = tempT;
                } else {
                    int subTemp = tempR;
                    tempR = m[y + 1][length - 1 - x];
                    m[y + 1][length - 1 - x] = subTemp;
                }
            }
            // BOTTOM EDGE
            for (int y = x; y < length - 1 - x; y++) {
                if ((y-x) == 0) {
                    tempB = m[length - 1 - x][length - 2 - y];
                    m[length - 1 - x][length - 2 - y] = tempR;
                } else {
                    int subTemp = tempB;
                    tempB = m[length - 1 - x][length - 2 - y];
                    m[length - 1 - x][length - 2 - y] = subTemp;
                }
            }
            // LEFT EDGE
            for (int y = x; y < length - 1 - x; y++) {
                if ((y-x) == 0) {
                    tempL = m[length- 2 - y][x];
                    m[length - 2 - y][x] = tempB;
                } else {
                    int subTemp = tempL;
                    tempL = m[length - 2 - y][x];
                    m[length - 2 - y][x] = subTemp;
                }
            }
        }
        return m;
    }
}


