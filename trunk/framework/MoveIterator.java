package framework;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Cette classe permet de creer un Iterator de mouvement retournant l'ensemble
 * de mouvement. Lors de la creation d'un MoveIterator il est possible
 * de fournir l'indice d'un mouvement qui doit etre retourne en premier.
 * @author Sebastien Drobisz.
 */
public class MoveIterator implements Iterator<Move> {
    private ArrayList<Move> moves;
    private int first;
    private int index;

    /**
     * Ce constructeur permet de creer un iterator de mouvements a partir d'une
     * liste de mouvements.
     * @param moves La liste des mouvements.
     */
    public MoveIterator(ArrayList<Move> moves) {
        initIterator(moves, -1);
    }

    /**
     * Ce constructeur permet d'initialiser un iterator de mouvement a partir
     * d'une liste de mouvement et de selectionne l'indice d'un mouvement
     * qui doit etre retourne en premier.
     * @param moves La liste des mouvements.
     * @param first L'indice du premier mouvement a retourner.
     */
    public MoveIterator(ArrayList<Move> moves, int first) {
        initIterator(moves, first);
    }

    /**
     * Permet d'initialiser un iterator avec une liste de mouvement et
     * l'indice du premier mouvement devant etre retourne.
     * @param moves La liste des mouvements.
     * @param first L'indice du premier mouvement a retourner.
     */
    private void initIterator(ArrayList<Move> moves, int first) {
        this.moves = moves;
        this.first = first;
        index = 0;
    }

    /**
     * Cette methode permet de savoir s'il reste un mouvement a retourner par
     * l'iterator.
     * @return true s'il y a encore un mouvement a retourner, false sinon.
     */
    public boolean hasNext() {
        return index < moves.size();
    }

    /**
     * Retourner le nombre de mouvement present dans l'iterator. Independament
     * du nombre de mouvement ayant deja ete retourne.
     */
    public int getNbMove() {
        return moves.size();
    }

    /**
     * Cette methode permet de retourner un mouvement suivant un indice donne.
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

    /**
     * Permet de retourner le prochain Mouvement s'il y en a encore qui n'ont
     * pas ete retourne.
     * @return Un mouvement n'ayant pas encore ete retourne.
     * @throws NoSuchElementException S'il n'y a plus de mouvement a retourner.
     */
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
