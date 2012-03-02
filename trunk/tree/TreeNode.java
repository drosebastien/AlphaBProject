package tree;

import java.util.ArrayList;

public class TreeNode {
    private TreeNode parentNode;
    private ArrayList<TreeNode> childNodes;
    private boolean isViewed;
    private String label;

    public TreeNode(TreeNode parentNode) {
        childNodes = new ArrayList<TreeNode>();
        this.parentNode = parentNode;
        isViewed = false;
    }

    public void setIsViewed(boolean isViewed) {
        this.isViewed = isViewed;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public TreeNode getParent() {
        return parentNode;
    }

    public void addChildNode(TreeNode childNode) {
        childNodes.add(childNode);
    }

    public int getNbChild() {
        return childNodes.size();
    }

    public TreeNode getChild(int index) {
        return childNodes.get(index);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
