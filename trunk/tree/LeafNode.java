package tree;

public class LeafNode extends TreeNode {
    private int value;

    public LeafNode(TreeNode parent, int value) {
        super(parent);

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
