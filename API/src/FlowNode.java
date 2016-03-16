public class FlowNode {

    public int prev;
    public boolean forward;

    public FlowNode(int node, boolean dir) {
        prev = node;
        forward = dir;
    }

    public String toString() {
        if (forward)
            return "" + prev + "->";
        else
            return "" + prev + "<-";
    }
}
