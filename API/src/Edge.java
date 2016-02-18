public class Edge implements Comparable<Edge> {

    int weight;
    int from;
    int to;

    public Edge(int v1, int v2, int weight) {
        this.from = v1;
        this.to = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        if (this.weight == edge.weight)
            return 0;
        return this.weight > edge.weight ? 1 : -1;
    }

    @Override
    public String toString() {
        return String.format("%d - %d - %d", from, weight, to);
    }
}
