public class DisjointSet {

    private Node[] nodes;

    public DisjointSet(int nodeCount) {
        nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node();
            nodes[i].id = i;
        }
    }

    /**
     * Unions sets, which contain vertices "a" and "b"
     * Union is always performed as merging "B" into "A"
     * @param a number of a node "a"
     * @param b number of a node "b"
     * @return number of a representative of the merged component
     */
    public int union(int a, int b) {
        Node repA = nodes[find(a)];
        Node repB = nodes[find(b)];
        repB.parent = repA;
        return repA.id;
    }

    /**
     * Returns representative of the compoment which contains the given node
     * @param a number of a node, whose representative we are looking for
     * @return number of the representative
     */
    public int find(int a) {
        Node n = nodes[a];
        int jumps = 0;
        while (n.parent != null) {
            n = n.parent;
            jumps++;
        }
        return n.id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            builder.append(find(i) + " ");
        }
        return builder.toString();
    }

    private static class Node {
        Node parent;
        int id;
    }
}
