package connectFour.evaluationFunction.tools;

import connectFour.*;
import framework.*;

/**
 * Cette classe permet a une ia qui evalue des coup à l'avance (tel qu'une ia
 * alpha beta ou minimax) d'avoir une representation claire des puissance 4
 * qui sont encore realiables pour elle et pour le joueur adverse ainsi
 * que leur état (0, 1, 2, 3 pions deja mis) et leurs directions tout en
 * faisant un minimum de calcules possible afin de ne pas penaliser
 * l'exploration 
 * @author Sebastien Drobisz
 */
public class C4Analyser {
    /**
     * Valeur donnee pour chaque pion place pour former un puissance 4.
     */
    public static final int NATURAL = 1;
    /**
     * Nombre de puissance 4 possibles dans une grille standard (7x6).
     */
    public static final int NBC4 = 69;
    /**
     * Indice du premier puissance 4 vertical dans le tableau ou est stocke
     * l'etat des puissance 4.
     */
    public static final int FIRST_V = 0;
    /**
     * Indice du premier puissance 4 horizontal dans le tableau ou est stocke
     * l'etat des puissance 4.
     */
    public static final int FIRST_H = 21;
    /**
     * Indice du premier puissance 4 diagonal croissant dans le tableau ou est
     * stocke l'etat des puissance 4.
     */
    public static final int FIRST_ID = 45;
    /**
     * Indice du premier puissance 4 diagonal décroissant dans le tableau ou est
     * stocke l'etat des puissance 4.
     */
    public static final int FIRST_DD = 57;

    private int[] player1Array;
    private int[] player2Array;
    private Player player1;
    private Player player2;
    private ConnectFour game;
    private CFBoard board;

    /**
     * Constructeur permettant d'initialiser l'analyser.
     * @param player1 Premier joueur (correspondant au joueur Max).
     * @param player2 Second joueur (correspondant au joueur Min).
     */
    public C4Analyser(Player player1, Player player2) {
        player1Array = new int[NBC4];
        player2Array = new int[NBC4];
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Permet d'initialiser les tableaux d'etats des deux joueurs.
     * doit se faire chaque fois avant de lancer l'exploration de l'algorithme.
     * @param game donne a l'algorithme pour lancer son exploration.
     */
    public void initializePossibleC4Array(ConnectFour game) {
        this.game = game;
        board = (CFBoard) game.getBoard();
        for(int i = 0; i < NBC4; i++) {
            player1Array[i] = 0;
            player2Array[i] = 0;
        }
        for(int i = 0; i < board.getWidth(); i++) {
            for(int j = 0; j < board.getHeight(); j++) {
                if(board.getPiece(new CFPosition(i, j)) != null) {
                    updateArray(i, j, NATURAL,
                                        board.getPiece(new CFPosition(i, j)));
                }
            }
        }
    }

    /**
     * Permet de retourner une chaine de caracteres representant les deux
     * tableaux d'etats.
     * Le premier etant celui du joueur 1 et le second celui du joueur 2.
     */
    public String toString() {
        String line = "------------------------------------------------\n";
        line += "Joueur 1 : \n";
        line += printTab(player1Array);

        line += "\nJoueur 2 : \n";
        line += printTab(player2Array);
        line += "------------------------------------------------";

        return line;
    }

    private String printTab(int[] tab) {
        String line = "| :\t";
        for(int i = FIRST_V; i < FIRST_H; i++) {
            line += tab[i] + " | ";
        }
        line += "\n- :\t";
        for(int i = FIRST_H; i < FIRST_ID; i++) {
            line += tab[i] + " | ";
        }
        line += "\n/ :\t";
        for(int i = FIRST_ID; i < FIRST_DD; i++) {
            line += tab[i] + " | ";
        }
        line += "\n\\ :\t";
        for(int i = FIRST_DD; i < NBC4; i++) {
            line += tab[i] + " | ";
        }
        return line + "\n";
    }

    /**
     * Permet de retourner l'etat d'un alignement pour le joueur 1.
     * @param x L'indice de l'alignement dont l'etat doit etre fourni.
     */
    public int getPlayer1Value(int x) {
        return player1Array[x];
    }

    /**
     * Permet de retourner l'etat d'un alignement pour le joueur 2.
     * @param x L'indice de l'alignement dont l'etat doit etre fourni.
     */
    public int getPlayer2Value(int x) {
        return player2Array[x];
    }

    /**
     * Permet de specifier qu'un mouvement a ete realise.
     * @param move le mouvement realise.
     */
    public void addToken(Move move) {
        CFPosition pos = ((CFMove) move).getPosition();
        Piece piece = ((CFMove) move).getPiece();
        int x = pos.getX();
        int y = pos.getY();
        updateArray(x, y, NATURAL, piece);
    }

    /**
     * Permet de specifier qu'un mouvement a ete annule.
     * @param move Le mouvement a annuler.
     */
    public void removeToken(Move move) {
        CFPosition pos = ((CFMove) move).getPosition();
        Piece piece = ((CFMove) move).getPiece();
        int x = pos.getX();
        int y = pos.getY();
        updateArray(x, y, -NATURAL, piece);
    }

    private void updateArray(int x, int y, int natural, Piece piece) {
        updateArrayV(x, y, natural, piece);
        updateArrayH(x, y, natural, piece);
        updateArrayCD(x, y, natural, piece);
        updateArrayDD(x, y, natural, piece);
    }

    private void updateArrayV(int x, int y, int natural, Piece piece) {
        int beginning = Math.max(0, y - 3);
        int ending = Math.min(y, 2);
        for(int j = beginning; j <= ending; j++) {
            int index = getIndexV(x, j);
            applyUpdate(index, natural, piece);
        }
    }

    private void updateArrayH(int x, int y, int natural, Piece piece) {
        int beginning = Math.max(0, x - 3);
        int ending = Math.min(x, 3);
        for(int i = beginning; i <= ending; i++) {
            int index = getIndexH(i, y);
            applyUpdate(index, natural, piece);
        }
    }

    private void updateArrayCD(int x, int y, int natural, Piece piece) {
        int beginningX = Math.max(0, x - 3);
        int endingX = Math.min(x, 3);
        int beginningY = Math.max(0, y - 3);
        int endingY = Math.min(y, 2);
        for(int i = beginningX; i <= endingX; i++) {
            for(int j = beginningY; j <= endingY; j++) {
                if((x - i) == (y - j)) {
                    int index = getIndexCD(i, j);
                    applyUpdate(index, natural, piece);
                }
            }
        }
    }

    private void updateArrayDD(int x, int y, int natural, Piece piece) {
        int beginningX = Math.max(x, 3);
        int endingX = Math.min(x + 3, 6);
        int beginningY = Math.max(0, y -3);
        int endingY = Math.min(y, 2);
        for(int i = beginningX; i <= endingX; i++) {
            for(int j = beginningY; j <= endingY; j++) {
                if((x - i) == (- y + j)) {
                    int index = getIndexDD(i, j);
                    applyUpdate(index, natural, piece);
                }
            }
        }
    }

    private int getIndexV(int i, int j) {
        return FIRST_V + j * board.getWidth() + i;
    }

    private int getIndexH(int i, int j) {
        return FIRST_H + i * board.getHeight() + j;
    }

    private int getIndexCD(int i, int j) {
        return FIRST_ID + j * 4 + i;
    }

    private int getIndexDD(int i, int j) {
        return FIRST_DD + j * 4 + (i - 3);
    }

    private void applyUpdate(int index, int natural, Piece piece) {
        if(piece.equals(player1.getPiece())) {
            player1Array[index] += natural;
            player2Array[index] -= 10 * natural;
        }
        else {
            player2Array[index] += natural;
            player1Array[index] -= 10 * natural;
        }
    }
}
