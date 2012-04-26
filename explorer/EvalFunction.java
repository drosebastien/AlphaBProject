package explorer;

import framework.*;

public abstract class EvalFunction implements MinMaxListener {

    public abstract int evalFunction(Game game, Player player);

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event) {}

    public void setValueOfNode(String value, MinMaxEvent event) {}

    public void refreshTree(MinMaxEvent event) {}

    public void setNewBestNode(int indexOfChild, MinMaxEvent event) {}

    public void setDropedNode(int indexOfChild, MinMaxEvent event) {}
}
