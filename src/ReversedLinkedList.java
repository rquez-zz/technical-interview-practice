class ReversedLinkedList {

    public static void main(String args[]) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        Node reversed = reverseList(node1, 2);
        System.out.println(stringifyList(reversed));
    }
    public static Node reverseList(Node node, int n) {
        return reverseList(node, n, 1);
    }
    public static Node reverseList(Node node, int n, int m) {
        if (node.getNext() == null) { return node; }
        Node nextNode = node.getNext();
        node.setNext(null);
        Node reversedNode;
        if ( n == m) {
            reversedNode = reverseList(nextNode, n, m);
            nextNode.setNext(node);
            return reversedNode;
        } else {
            reversedNode = reverseList(nextNode, n, m + 1);
            Node helper = reversedNode;
            node.setNext(helper.getNext());
            while(helper.getNext() != null) {
                helper = helper.getNext();
            }
            helper.setNext(reversedNode);
            reversedNode.setNext(null);
            return node;
        }
    }
    public static String stringifyList(Node root) {
        if (root.getNext() == null) {
            return Integer.toString(root.getData());
        }
        return root.getData() + " " + stringifyList(root.getNext());
    }
}
