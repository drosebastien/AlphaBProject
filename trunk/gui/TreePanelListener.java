package gui;

public interface TreePanelListener {

    public void clickOnNode(boolean isInExplorerMode, int[] path);

    public void bestNodeSelected(boolean isInExplorerMode, int[] path);

    public void preview(int[] moves, boolean inExplorerMode);

    public void quitPreview();
}