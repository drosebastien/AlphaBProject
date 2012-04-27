package explorer;

public interface MinMaxListener {

    public void started();

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event);

    public void setValueOfNode(String value, MinMaxEvent event);

    public void refreshTree(MinMaxEvent event);

    public void setNewBestNode(int indexOfChild, MinMaxEvent event);

    public void setDropedNode(int indexOfChild, MinMaxEvent event);
}
