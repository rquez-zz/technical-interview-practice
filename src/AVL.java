import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 10/6/15.
 */
public class AVL extends BST{

    public AVL(AVLNode root) {
        super(root);
    }

    public void insert(int data) { setRoot(insert((AVLNode) getRoot(), data)); }
    public AVLNode insert(AVLNode node, int data) {

        if (node == null) node = new AVLNode(data);
        else if(node.getData() > data) {
            node.setLeft(insert(node.getLeft(), data));
            if (!isBalanced(node.getLeft(), node.getRight())) {
                if (node.getLeft().getData() < data) node = LRRotate(node);
                else node = RRotate(node);
            }
        } else if(node.getData() < data) {
            node.setRight(insert(node.getRight(), data));
            if (!isBalanced(node.getRight(), node.getLeft())) {
                if (node.getRight().getData() < data)
                    node = LRotate(node);
                else
                    node = RLRotate(node);
            }
        } else { }

        node.setHeight(max(treeHeight(node.getLeft()), treeHeight(node.getRight())) + 1);
        return node;
    }

    public AVLNode RRotate(AVLNode node) {

        AVLNode z = node;
        AVLNode y = node.getLeft();
        AVLNode x = node.getLeft().getLeft();
        z.setLeft(y.getRight());
        y.setRight(z);
        y.setLeft(x);
        return y;
    }

    public AVLNode LRotate(AVLNode node) {

        AVLNode z = node;
        AVLNode y = node.getRight();
        AVLNode x = node.getRight().getRight();
        z.setRight(y.getLeft());
        y.setLeft(z);
        y.setRight(x);
        return y;
    }

    public AVLNode LRRotate(AVLNode node) {
        return RRotate(LRotate(node.getLeft()));
    }

    public AVLNode RLRotate(AVLNode node) {
        return LRotate(RRotate(node.getRight()));
    }

    public boolean isBalanced() {
        return Math.abs(treeHeight((AVLNode) getRoot().getLeft()) - treeHeight((AVLNode) getRoot().getRight())) < 2;
    }
    public boolean isBalanced(AVLNode a, AVLNode b) {
       return (treeHeight(a) - treeHeight(b)) < 2;
    }

    public int max(int a, int b) {
        return (a > b ? a : b);
    }

    public int treeHeight(AVLNode node) {
        if (node == null) return 0;
        return node.getHeight();
    }

    public void remove(int data) { setRoot(remove((AVLNode)getRoot(), data)); }
    public AVLNode remove(AVLNode node, int data) {

        if (node.getData() == data) return null;
        else if(node.getData() > data) {
            node.setLeft(remove(node.getLeft(), data));
            if (!isBalanced(node.getRight(), node.getLeft())) {
                if (node.getRight().getRight() != null) node = LRotate(node);
                else node = RLRotate(node);
            }
        } else if(node.getData() < data) {
            node.setRight(remove(node.getRight(), data));
            if (!isBalanced(node.getLeft(), node.getRight())) {
                if (node.getLeft().getLeft() != null) node = RRotate(node);
                else node = LRRotate(node);
            }
        }

        node.setHeight(max(treeHeight(node.getLeft()), treeHeight(node.getRight())) - 1);
        arrangeHeights(node, node.getHeight());
        return node;
    }
    public int arrangeHeights(AVLNode node, int height) {
        if (node.getLeft() != null) {
            node.getLeft().setHeight(arrangeHeights(node.getLeft(), height - 1));
        }
        if(node.getRight() != null) {
            node.getRight().setHeight(arrangeHeights(node.getRight(),height - 1));
        }
        return height;

    }

    public static class UnitTest {
        @Test
        public void testInsert() {
            AVL tree1 = new AVL(new AVLNode(10));
            tree1.insert(100);
            tree1.insert(800);
            assertEquals(true, tree1.isBalanced());

            AVL tree2 = new AVL(new AVLNode(100));
            tree2.insert(50);
            tree2.insert(25);
            assertEquals(true, tree2.isBalanced());

            AVL tree3 = new AVL(new AVLNode(100));
            tree3.insert(50);
            tree3.insert(75);
            assertEquals(true, tree3.isBalanced());

            AVL tree4 = new AVL(new AVLNode(10));
            tree4.insert(100);
            tree4.insert(75);
            assertEquals(true, tree4.isBalanced());

            AVL tree5 = new AVL(new AVLNode(10));
            tree5.insert(100);
            tree5.insert(75);
            tree5.insert(25);
            tree5.insert(1);
            tree5.insert(1000);
            assertEquals(true, tree5.isBalanced());
        }



        @Test
        public void testRemove() {
            AVL tree1 = new AVL(new AVLNode(10));
            tree1.insert(100);
            tree1.insert(800);
            tree1.insert(5);
            tree1.remove(800);
            assertEquals(true, tree1.isBalanced());
        }
    }
}
