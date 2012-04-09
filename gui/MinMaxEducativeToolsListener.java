package gui;

import explorer.MinMaxAlgo;

public interface MinMaxEducativeToolsListener {
    public void progress(boolean inExplorerMode);

    public void removeLast(boolean inExplorerMode);

    public void treeDepthChanged(int treeDepth);

    public void algoHaveChanged(String algoName);

    public void windowValuesHaveChanged(int minValue, int maxValue);

    public void play(boolean inExplorerMode);

    public void pause(boolean inExplorerMode);

    public void stop(boolean inExplorerMode);
}
