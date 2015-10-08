import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 10/7/15.
 */
public class Graph {

    private final int[][] adjencyMatrix;

    public Graph() {
        this.adjencyMatrix = new int[5][5];
    }
    public Graph(int[][] adjencyMatrix) {
        this.adjencyMatrix = adjencyMatrix;
    }

    public String DFS(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited  = new boolean[this.adjencyMatrix[start].length];
        Stack<Integer> stack = new Stack();
        visited[start] = true;
        stack.push(start);
        sb.append(start + " ");
        while(!stack.isEmpty()) {
            int node = getUnvisitedNode(this.adjencyMatrix, visited, stack.peek());
            if (node != -1) {
                visited[node] = true;
                stack.push(node);
                sb.append(node + " ");
            } else
                stack.pop();
        }
        return sb.toString();
    }

    public String DFS(int node, boolean[] visited) {
        if (node == -1)
            return "";
        visited[node] = true;
        return node + " " + DFS(getUnvisitedNode(this.adjencyMatrix, visited, node), visited);
    }

    public String BFS(int start) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[this.adjencyMatrix[start].length];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        sb.append(start + " ");
        while(!queue.isEmpty()) {
            int node = getUnvisitedNode(this.adjencyMatrix, visited, queue.getFirst());
            if (node != -1) {
                visited[node] = true;
                queue.add(node);
                sb.append(node + " ");
            } else
                queue.removeFirst();
        }
        return sb.toString();
    }

    private int getUnvisitedNode(int[][] adjencyMatrix, boolean[] visited, int node) {
        for (int i = 0; i < adjencyMatrix[node].length; i++) {
            if (adjencyMatrix[node][i] > 0 && !visited[i]) {
                return i;
            }
        }
        return -1;
    }

    public static class UnitTest {
        private int[][] matrix;
        @Before
        public void setup() {

            matrix = new int[5][5];

            matrix[0][0] = 0;
            matrix[0][1] = 1;
            matrix[0][2] = 3;
            matrix[0][3] = -1;
            matrix[0][4] = -1;

            matrix[1][0] = 1;
            matrix[1][1] = 0;
            matrix[1][2] = -1;
            matrix[1][3] = 7;
            matrix[1][4] = 2;

            matrix[2][0] = 3;
            matrix[2][1] = -1;
            matrix[2][2] = 0;
            matrix[2][3] = 2;
            matrix[2][4] = 1;

            matrix[3][0] = -1;
            matrix[3][1] = 7;
            matrix[3][2] = 2;
            matrix[3][3] = 0;
            matrix[3][4] = -1;

            matrix[4][0] = -1;
            matrix[4][1] = 2;
            matrix[4][2] = 1;
            matrix[4][3] = -1;
            matrix[4][4] = 0;
        }
        @Test
        public void testDFS() {

            Graph graph = new Graph(matrix);
            assertEquals("0 1 3 2 4 ", graph.DFS(0));
            assertEquals("3 1 0 2 4 ", graph.DFS(3));
            assertEquals("3 1 0 2 4 ", graph.DFS(3, new boolean[5]));
            assertEquals("0 1 3 2 4 ", graph.DFS(0, new boolean[5]));
        }
        @Test
        public void testBFS() {

            Graph graph = new Graph(matrix);
            assertEquals("0 1 2 3 4 ", graph.BFS(0));

        }
    }

}
