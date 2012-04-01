package gui;

public interface MinMaxEducativeToolsListener {
    public void progress(boolean inExplorerMode);

    public void removeLast(boolean inExplorerMode);

    public void treeDepthChanged(int treeDepth);
}
