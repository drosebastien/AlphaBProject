package explorer;

public interface MinMaxListener {

    public void moved(Movement move, int indexInTreeGame);

    public void setValueOfNode(String value);

    public void refreshTree();

    public void setNewBestNode(int indexOfChild);

    public void setDropedNode(int indexOfChild);
}