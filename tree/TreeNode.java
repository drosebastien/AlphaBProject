package tree;

import java.util.ArrayList;

/**
 * Cette classe permet de representer le noeud d'un arbre.
 * @author Sebastien Drobisz.
 */
public class TreeNode {
    private TreeNode parentNode;
    private ArrayList<TreeNode> childNodes;
    private String label;
    private NodeType type;

    /**
     * Ce constructeur permet de creer le noeud d'un arbre en lui passant
     * son noeud pere.
     * @param parentNode Son noeud pere.
     */
    public TreeNode(TreeNode parentNode) {
        childNodes = new ArrayList<TreeNode>();
        this.parentNode = parentNode;
        type = NodeType.NEITHER;
    }

    /**
     * Cette methode permet de changer le type du noeud.
     * @param type le type du noeud.
     */
    public void setType(NodeType type) {
        this.type = type;
    }

    /**
     * Cette methode permet de retourner le type du noeud.
     * @return Le type du noeud.
     */
    public NodeType getType() {
        return type;
    }

    /**
     * Cette methode permet d'obtenir le noeud pere du noeud.
     * @return Le noeud pere.
     */
    public TreeNode getParent() {
        return parentNode;
    }

    /**
     * Cette methode permet d'ajouter un fils au noeud.
     * @param childNode LE noeud fils a ajouter.
     */
    public void addChildNode(TreeNode childNode) {
        childNodes.add(childNode);
    }

    /**
     * Cette methode permet de connaitre le nombre de fils que possede une noeud
     * @return Le nombre de fils.
     */
    public int getNbChild() {
        return childNodes.size();
    }

    /**
     * Cette methode permet d'obtenir un fils du noeud.
     * @param index l'indice du noeud voulu.
     * @return Le noeud voulu.
     */
    public TreeNode getChild(int index) {
        return childNodes.get(index);
    }

    /**
     * Cette methode permet de donner un label au noeud.
     * @param label le label a donner.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Cette methode permet de supprimer le lable du noeud.
     */
    public void removeLabel() {
        this.label = null;
    }

    /**
     * Cette methode permet d'obtenir le label du noeud.
     * @return Le label du noeud.
     */
    public String getLabel() {
        return label;
    }
}
