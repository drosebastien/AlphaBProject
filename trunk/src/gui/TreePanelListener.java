package gui;

/**
 * Cette interface permet a une classe d'ecouter les actions realisees sur
 * le panel de l'arbre d'exploration.
 */
public interface TreePanelListener {

    /**
     * Cette methode permet de prevenir qu'un click a ete realise sur un noeud.
     * @param isInExplorerMode true si dans le mode explorer, false si dans
     * le mode execution de l'algorithme.
     * @param path le chemin menant au noeud sur lequel un click a ete fait.
     */
    public void clickOnNode(boolean isInExplorerMode, int[] path);

    /**
     * Cette methode permet de prevenir qu'un noeud doit etre evalue avant les
     * autre.
     * @param isInExplorerMode true si dans le mode explorer, false si dans
     * le mode execution de l'algorithme.
     * @param path Le chemin menant au noeud devant etre evalue en premier.
     */
    public void bestNodeSelected(boolean isInExplorerMode, int[] path);

    /**
     * Cette methode permet de prevenir que le mode apercu a ete fait.
     * @param path Le chemin menant au noeud dont l'apercu est demande.
     * @param inExplorerMode true si dans le mode explorer, false si dans
     * le mode execution de l'algorithme.
     */
    public void preview(int[] path, boolean inExplorerMode);

    /**
     * Permet de prevenir que l'etat d'apercu a ete quitte.
     */
    public void quitPreview();
}
