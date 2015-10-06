public class Node {
    private int data;
    private Node next;
    private Node left;
    private Node right;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
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
}
