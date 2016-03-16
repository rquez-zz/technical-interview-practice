public class FlowEdge {

    private int capacity;
    private int flow;

    public FlowEdge(int capacity) {
        this.capacity = capacity;
        this.flow = 0;
    }

    public int maxPushForward() {
        return capacity - flow;
    }

    public int maxPushBackward() {
        return flow;
    }

    public boolean pushForward(int moreflow) {

        // We can't push through this much flow.
        if (this.flow + moreflow > this.capacity)
            return false;

        // Push through.
        flow += moreflow;

        return true;
    }

    public boolean pushBack(int lessflow) {

        // Not enough to push back on.
        if (this.flow < lessflow)
            return false;

        this.flow -= lessflow;

        return true;
    }
}
