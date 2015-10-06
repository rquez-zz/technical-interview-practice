import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 10/6/15.
 */
public class BST {

    private Node root;

    public BST (Node root) {
        this.root = root;
        this.root.setLeft(null);
        this.root.setRight(null);
    }

    public void insert(int data) { insert(this.root, data); }
    private Node insert(Node node, int data) {
       if (node == null) {
           Node newNode = new Node(data, null);
           return newNode;
       }
        if (node.getData() > data) {
            node.setLeft(insert(node.getLeft(), data));
        } else {
            node.setRight(insert(node.getRight(), data));
        }
        return node;
    }

    public void remove(int data) { this.root = remove(this.root, data); }
    private Node remove(Node node, int data) {
        if (node.getData() == data) {
            if(node.getLeft() != null && node.getRight() != null) {
                Node replacement = node.getRight();
                replacement.setLeft(node.getLeft());
                node.setLeft(null);
                node.setRight(null);
                return replacement;
            } else if (node.getLeft() != null) {
                Node replacement = node.getLeft();
                node.setLeft(null);
                return replacement;
            } else if (node.getRight() != null) {
                Node replacement = node.getRight();
                node.setRight(null);
                return replacement;
            } else
                return null;
        }

        if (node.getData() > data) {
            node.setLeft(remove(node.getLeft(), data));
        } else {
            node.setRight(remove(node.getRight(), data));
        }
        return node;
    }

    public String inorder() { return inorder(this.root); }
    private String inorder(Node node) {

        if (node == null) return "";
        return inorder(node.getLeft()) + node.getData() + " " + inorder(node.getRight());
    }

    public String postorder() { return postorder(this.root); }
    private String postorder(Node node) {

        if (node == null) return "";
        return postorder(node.getLeft()) + postorder(node.getRight()) + node.getData() + " ";
    }

    public String preorder() { return preorder(this.root); }
    private String preorder(Node node) {

        if (node == null) return "";
        return node.getData() + " " + preorder(node.getLeft()) + preorder(node.getRight());
    }

    public static class UnitTest {
        @Test
        public void testInsert() {
            Node node = new Node(10, null);
            BST tree = new BST(node);
            tree.insert(5);
            tree.insert(15);
            tree.insert(20);
            tree.insert(100);
            tree.insert(1);
            assertEquals("1 5 10 15 20 100 ", tree.inorder());
            assertEquals("10 5 1 15 20 100 ", tree.preorder());
            assertEquals("1 5 100 20 15 10 ", tree.postorder());
        }
        @Test
        public void testRemove() {
            Node node = new Node(10, null);
            BST tree = new BST(node);
            tree.insert(5);
            tree.insert(15);
            tree.insert(20);
            tree.insert(100);
            tree.insert(1);
            tree.remove(1);
            assertEquals("5 10 15 20 100 ", tree.inorder());
            tree.remove(20);
            assertEquals("5 10 15 100 ", tree.inorder());
            tree.remove(10);
            assertEquals("5 15 100 ", tree.inorder());
        }
    }
}
