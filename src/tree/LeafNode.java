package tree;

/**
 * Cette class permet de representer une feuille d'un arbre.
 * @author Sebastien Drobisz
 */
public class LeafNode extends TreeNode {
    private int value;

    /**
     * Ce constructeur permet de creer une feuille en lui passant une valeur
     * et son noeud pere.
     * @param parent Le noeud pere.
     * @param value la valeur.
     */
    public LeafNode(TreeNode parent, int value) {
        super(parent);

        this.value = value;
    }

    /**
     * Cette methode permet de retourner la valeur attribuee a la feuille.
     * @return la valeur de la feuille.
     */
    public int getValue() {
        return value;
    }
}
