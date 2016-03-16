import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class NetworkFlow {

    private final static int INF = Integer.MAX_VALUE;

    private FlowEdge[][] adjMat;
    private int source;
    private int dest;

    public NetworkFlow(int[][] capacities, int start, int end) {

        source = start;
        dest = end;
        adjMat = new FlowEdge[capacities.length][capacities.length];

        // Arrange adjency matrix from given flow capacities
        for (int i = 0; i< capacities.length; i++) {
            for (int j = 0; j< capacities[i].length; j++) {

                // Fill in this flow.
                if (capacities[i][j] > 0)
                    adjMat[i][j] = new FlowEdge(capacities[i][j]);
                else
                    adjMat[i][j] = null;
            }
        }
    }

    /**
     * Finds the path of nodes that can be augmented in flow
     * @return A List of nodes in order from start to destination
     */
    public ArrayList<FlowNode> findAugmentingPath() {

        // This will store the previous node visited in the BFS.
        FlowNode[] prev = new FlowNode[adjMat.length];
        boolean[] inQueue = new boolean[adjMat.length];

        // The source has no previous node.
        prev[this.source] = new FlowNode(-1, true);

        LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.offer(source);
        inQueue[source] = true;

        // Our BFS will go until we clear out the queue.
        while (bfsQueue.size() > 0) {

            // Add all the new neighbors of the current node.
            Integer next = bfsQueue.poll();

            // Find all neighbors and add into the queue. These are forward edges.
            for (int i = 0; i < adjMat.length; i++) {
                if (!inQueue[i] && adjMat[next][i] != null && adjMat[next][i].maxPushForward() > 0) {
                    bfsQueue.offer(new Integer(i));
                    inQueue[i] = true;
                    prev[i] = new FlowNode(next, true);
                }
            }

            // Now look for back edges.
            for (int i=0; i<adjMat.length; i++) {
                if (!inQueue[i] && adjMat[i][next] != null && adjMat[i][next].maxPushBackward() > 0) {
                    bfsQueue.offer(new Integer(i));
                    inQueue[i] = true;
                    prev[i] = new FlowNode(next, false);
                }
            }
        }

        // No augmenting path found.
        if (!inQueue[dest])
            return null;

        ArrayList<FlowNode> path = new ArrayList<FlowNode>();

        FlowNode place = prev[dest];

        FlowNode dummy = new FlowNode(dest, true);
        path.add(dummy);

        // Build the path backwards.
        while (place.prev != -1) {
            path.add(place);
            place = prev[place.prev];
        }

        // Reverse it now.
        Collections.reverse(path);

        return path;
    }

    /**
     * Finds the maximum flow
     * @return an int representing the max flow
     */
    public int getMaxFlow() {

        int flow = 0;

        // Loop until there are no more augmenting paths.
        for (ArrayList<FlowNode> nextpath = findAugmentingPath(); nextpath != null; nextpath = findAugmentingPath()) {

            // Check what the best flow through this path is.
            int this_flow = INF;
            for (int i = 0; i < nextpath.size() - 1; i++) {
                if (nextpath.get(i).forward) {
                    this_flow = Math.min(this_flow, adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].maxPushForward());
                } else {
                    this_flow = Math.min(this_flow, adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].maxPushBackward());
                }
            }

            // Now, put this flow through.
            for (int i= 0; i < nextpath.size() - 1; i++) {

                if (nextpath.get(i).forward) {
                    adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].pushForward(this_flow);
                } else {
                    adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].pushBack(this_flow);
                }
            }

            // Add this flow in and then get the next path.
            flow += this_flow;
        }

        return flow;
    }

    public static class NetworkFlowTest {

        @Test
        public void test_getMaxFlow() {

            int[][] graph = new int[6][6];
            graph[0][1] = 16;
            graph[0][2] = 13;
            graph[1][2] = 10;
            graph[2][1] = 4;
            graph[1][3] = 12;
            graph[3][2] = 9;
            graph[2][4] = 14;
            graph[3][5] = 20;
            graph[4][3] = 7;
            graph[4][5] = 4;

            NetworkFlow network = new NetworkFlow(graph, 0, 5);

            int result = network.getMaxFlow();
            System.out.println(result);
        }
    }
}

