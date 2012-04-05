package framework;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MoveIterator implements Iterator<Move> {
    private ArrayList<Move> moves;
    private int first;
    private int index;

    public MoveIterator(ArrayList<Move> moves) {
        initIterator(moves, -1);
    }

    public MoveIterator(ArrayList<Move> moves, int first) {
        initIterator(moves, first);
    }

    private void initIterator(ArrayList<Move> moves, int first) {
        this.moves = moves;
        this.first = first;
        index = 0;
    }

    public boolean hasNext() {
        return index < moves.size();
    }

    public int getNbMove() {
        return moves.size();
    }

    /**
     * Cette méthode permet de retourner un mouvement suivant un indice donné.
     * pour ce faire, le retour du mouvement se fait en O(1).
     * @param i L'indice du mouvement voulu.
     * @return Le mouvement voulu.
     */
    public Move getMove(int i) throws MoveException {
        if(i >= getNbMove() || i < 0) {
            throw new MoveException();
        }

        if(first >= 0) {
            if(i == 0) {
                return moves.get(first);
            }
            else if(i <= first) {
                return moves.get(i - 1);
            }
            else {
                return moves.get(i);
            }
        }

        return moves.get(i);
    }

    public Move next() throws NoSuchElementException {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        Move selectMove = null;
        if(index == 0 && first >= 0 && first < moves.size()) {
            selectMove = moves.get(first);
        }
        else if(index -1 < first) {
            selectMove = moves.get(index - 1);
        }
        else {
            selectMove = moves.get(index);
        }
        index++;

        return selectMove;
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
            "AlignementIterator does not support \"remove\" operation.");
    }
}
