package framework;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MoveIterator implements Iterator<Move> {
    private ArrayList<Move> moves;
    private int index;

    public MoveIterator(ArrayList<Move> moves) {
        this.moves = moves;
        index = 0;
    }

    public boolean hasNext() {
        return index < moves.size();
    }

    public Move next() throws NoSuchElementException {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        return moves.get(index++);
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
            "AlignementIterator does not support \"remove\" operation.");
    }
}
