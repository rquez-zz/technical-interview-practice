/**
 * Created by ricardo on 10/6/15.
 */
public class AVLNode extends Node {

    private int height;
    private AVLNode left;
    private AVLNode right;

    @Override
    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    @Override
    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode(int data) {
        super(data);
        this.height = 1;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
