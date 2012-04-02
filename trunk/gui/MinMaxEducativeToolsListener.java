package gui;

import explorer.MinMaxAlgo;

public interface MinMaxEducativeToolsListener {
    public void progress(boolean inExplorerMode);

    public void removeLast(boolean inExplorerMode);

    public void treeDepthChanged(int treeDepth);

    public void algoHaveChanged(MinMaxAlgo algo);
}
