/*
 Given a 2-dimensional square matrix, rotate the matrix clockwise.
 Imagine concentric circles.
 Input from stdin: first line is length, subsequent lines are rows of the matrix.
 Output the matrix to stdout.
*/
public class MatrixRotation {
    public static void main(String args[]) {
        int[][] m = new int[3][3];
        m[0] = new int[] {1, 2, 3};
        m[1] = new int[] {8, 9, 4};
        m[2] = new int[] {7, 6, 5};

        printMatrix(rotateMatrix(m));
        System.out.println();

        m = new int[5][5];
        m[0] = new int[] {1, 2, 3, 4, 5};
        m[1] = new int[] {6, 1, 2, 3, 6};
        m[2] = new int[] {5, 8, 0, 4, 7};
        m[3] = new int[] {4, 7, 6, 5, 8};
        m[4] = new int[] {3, 2, 1, 0, 9};

        printMatrix(rotateMatrix(m));
        System.out.println();

        m = new int[6][6];
        m[0] = new int[] {1, 2, 3, 4, 5, 6};
        m[1] = new int[] {0, 1, 2, 3, 4, 7};
        m[2] = new int[] {9, 2, 1, 2, 5, 8};
        m[3] = new int[] {8, 1, 4, 3, 6, 9};
        m[4] = new int[] {7, 0, 9, 8, 7, 0};
        m[5] = new int[] {6, 5, 4, 3, 2, 1};

        printMatrix(rotateMatrix(m));
    }

    public static void printMatrix(int[][] m) {
        for (int[] a : m) {
            for (int b: a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }

    public static int[][] rotateMatrix(int[][] m) {
        int length = m[0].length;
        int circles = length / 2;
        for (int x = 0; x < circles; x++) {
            int temp1 = 0, temp2 = 0 , temp3 = 0 , temp4 = 0;
            for (int y = x; y < length - 1 - x; y++) {
                if ((y-x) == 0) {
                    temp1 = m[x][y + 1]; // top
                    temp2 = m[y + 1][length - 1 - x]; // right edge
                    temp3 = m[length - 1 - x][length - 2 - y]; // bottom edge
                    temp4 = m[length - 2 - y][x]; // left

                    m[x][y + 1] = m[x][y];
                    m[y + 1][length - 1 - x] = m[y][length - 1 - x];
                    m[length - 1 - x][length - 2 - y] = m[length - 1 - x][length - 1 - y];
                    m[length - 2 - y][x] = m[length - 1 - y][x];
                } else {
                    int subTemp1 = m[x][y + 1];
                    int subTemp2 = m[y + 1][length - 1 - x];
                    int subTemp3 = m[length - 1 - x][length - 2 - y];
                    int subTemp4 = m[length - 2 - y][x];

                    m[x][y + 1] = temp1;
                    m[y + 1][length - 1 - x] = temp2;
                    m[length - 1 - x][length - 2 - y] = temp3;
                    m[length - 2 - y][x] = temp4;

                    temp1 = subTemp1;
                    temp2 = subTemp2;
                    temp3 = subTemp3;
                    temp4 = subTemp4;
                }
            }
        }
        return m;
    }
}


