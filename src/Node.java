public class Node {
    private int data;
    private Node next;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    public int getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getLeft() { return this.left; }

    public Node getRight() { return this.right; }

    public void setLeft(Node node) { this.left = node; }

    public void setRight(Node node) { this.right = node; }

    public static boolean isEqual(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        } if (node1.getData() == node2.getData()) {
            return isEqual(node1.getNext(), node2.getNext());
        } else {
            return false;
        }
    }
}
