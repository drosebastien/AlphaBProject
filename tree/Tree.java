package tree;

import java.util.ArrayList;

public class Tree {
    public static int getNbNodeToDepth(int depth, TreeNode node) {
        int nbNodes = 0;

        if(depth == 0 || node.getNbChild() == 0) {                              // si 2 ème condition, il doit lancer une erreur.
            //System.out.println(((LeafNode) node).getValue());
            return 1;
        }
        else {
            for(int i = 0; i < node.getNbChild(); i++) {
                nbNodes += getNbNodeToDepth(depth - 1, node.getChild(i));
            }
        }

        return nbNodes;
    }

    public static void removeStatesOfTree(TreeNode node) {
        node.setType(NodeType.NEITHER);
        node.removeLabel();
        for(int i = 0; i < node.getNbChild(); i++) {
            removeStatesOfTree(node.getChild(i));
            
        }
    }

    public static TreeNode getNode(ArrayList<Integer> pos, TreeNode node) {
        if(pos.size() == 0) {
            return node;
        }

        TreeNode childNode = node.getChild(pos.remove(0));
        return getNode(pos, childNode);
    }

    public static int getMaximumDepth(TreeNode node) {
        int max = 0;

        if(node.getNbChild() == 0) {
            return 1;
        }

        for(int i = 0; i < node.getNbChild(); i++) {
            int subTreeDepth = getMaximumDepth(node.getChild(i));
            if(max < subTreeDepth) {
                max = subTreeDepth;
            }
        }

        return max + 1;
    }

    public static TreeNode makeTree(int depth, int breath) {
        if(depth == 1) {
            return new LeafNode(null, 42);
        }

        TreeNode root = new TreeNode(null);
        makeTree(depth, breath, root);
        return root;
    }

    private static void makeTree(int depth, int breath, TreeNode parent) {
        if(depth == 2) {
            for(int i = 0; i < breath; i++) {
                LeafNode node = new LeafNode(parent, i);
                parent.addChildNode(node);
            }
        }
        else {
            for(int i = 0; i < breath; i++) {
                TreeNode node = new TreeNode(parent);
                makeTree(depth - 1, breath, node);
                parent.addChildNode(node);
            }
        }
    }
}
