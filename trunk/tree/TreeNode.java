package tree;

import java.util.ArrayList;

public class TreeNode {
    private TreeNode parentNode;
    private ArrayList<TreeNode> childNodes;
    private String label;
    private NodeType type;

    public TreeNode(TreeNode parentNode) {
        childNodes = new ArrayList<TreeNode>();
        this.parentNode = parentNode;
        type = NodeType.NEITHER;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public NodeType getType() {
        return type;
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

    public void removeLabel() {
        this.label = null;
    }

    public String getLabel() {
        return label;
    }
}
