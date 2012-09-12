package framework;

import java.util.ArrayList;
import gui.GamePanel;

/**
 * Cette classe permet de creer le comportement d'un jeu.
 * @author Sebastien Drobisz
 */
public abstract class Game {
    protected ArrayList<Player> listOfPlayers;
    protected int depthToSelectState;
    protected int[] firsts;
    protected Board board;
    protected GamePanel gamePanel;

    /**
     * Constructeur permettant d'initialiser un game avec un board donne.
     * @param board Le board permettant d'initialiser le jeu.
     */
    public Game(Board board) {
        listOfPlayers = new ArrayList<Player>();
        this.board = board;
        saveStateOfGame();
    }

    /**
     * Methode permettant d'ajouter un joueur au jeu.
     * @param player Le joueur qui doit etre ajoute.
     */
    public void addPlayer(Player player) {
        if(player instanceof HumanPlayer) {
            gamePanel.addListener((HumanPlayer) player);
        }
        listOfPlayers.add(player);
    }

    /**
     * Cette methode permet de distribuer les pieces aux joueurs pour que ceux-
     * ci puisse jouer.
     */
    public abstract void piecesDistribution();

    /**
     * Retourne le nombre minimum de joueur pouvant jouer a un jeu.
     * @return Le nombre minimum de joueur pouvant jouer a un jeu.
     */
    public abstract int getNbMinPlayer();

    /**
     * Retourne le nombre maximum de joueur pouvant jouer a un jeu.
     * @return Le nombre maximum de joueur pouvant jouer a un jeu.
     */
    public abstract int getNbMaxPlayer();

    /**
     * Cette methode permet de retourner l'ensemble de tous les joueurs inscrits
     * au jeu.
     * @return La liste de tous les joueurs participant a une partie de ce jeu.
     */
    public ArrayList<Player> getListOfPlayer() {
        return listOfPlayers;
    }

    /**
     * Cette methode permet de savoir c'est au tour de quel joueur de jouer.
     * @return Le prochain joueur devant jouer.
     */
    public abstract Player nextPlayer();

    /**
     * Cette methode permet de jouer un coup a un jeu.
     * @param move Le coup devant etre joue.
     * @throws MoveException Lance une exception s'il n'est pas possible de
     * realiser ce mouvement.
     */
    public abstract void play(Move move) throws MoveException;

    /**
     * Cette methode permet d'annuler un coup precedemment joue.
     * @param move le coup devant etre annule.
     */
    public abstract void removeMove(Move move);

    /**
     * Cette methode permet de complementer un mouvement incomplet en y
     * ajoutant la piece appartenant au prochain joueur devant jouer.
     * @param move Le mouvement a completer.
     */
    public abstract Move completeMove(Move move);

    /**
     * Cette methode permet de prevenir le jeu que certains coups doivent etre
     * donne en premier lors que l'ensemble des coups jouables est demande.
     * @param firsts L'ensemble des coups devant etre retourne en premier.
     */
    public void setFirstMovesOfPossibleMoves(int[] firsts) {
        if(this.firsts != null) {
            int minLength = (firsts.length < this.firsts.length)?
                            firsts.length: this.firsts.length;
            for(int i = 0; i < minLength; i++) {
                if(firsts[i] == 0) {
                    firsts[i] = this.firsts[i];
                }
                else if(firsts[i] <= this.firsts[i]) {
                    firsts[i]--;
                }
            }
        }
        this.firsts = firsts;
        this.depthToSelectState = 0;
    }

    /**
     * Cette methode permet d'annuler la selection de premiers coups.
     */
    public void resetFirstMovesOfPossibleMove() {
        firsts = null;
        this.depthToSelectState = 0;
    }

    /**
     * Permet de retourner l'ensemble des mouvements possibles.
     * @return L'ensemble des coups possibles.
     */
    public abstract MoveIterator getPossibleMoves();

    /**
     * Permet de retourner la validite d'un mouvement.
     * @param move retourne true si le mouvement peut etre joue. Sinon retourne
     * false.
     */
    public abstract boolean isPossibleMove(Move move);

    /**
     * Cette methode permet de savoir si une partie est terminee.
     * @return true si la partie est finie, false sinon.
     */
    public abstract boolean isFinish();

    /**
     * Cette methode permet de savoir la partie est en etat de victoire.
     * @return true si un joueur a gagne, false sinon.
     */
    public abstract boolean isVictory();

    /**
     * Cette methode permet de sauvegarder l'etat d'un jeu.
     */
    public abstract void saveStateOfGame();

    /**
     * permet de recharger l'etat d'un jeu precedemment sauvegarde.
     */
    public void loadSavedState() {
        depthToSelectState = 0;
    }

    /**
     * Permet de sauvegarder un etat du jeu et de fournir cette sauvegarde
     * A l'instance de classe reclamant cette action.
     * @return L'etat du jeu.
     */
    public abstract GameMemento saveToMemento();

    /**
     * Permet de recharger un etat precedemment fourni.
     * @param memento L'etat a recharger.
     */
    public abstract void resetFromMemento(GameMemento memento);

    /**
     * Cette methode permet de retourner le joueur qui a gagne la partie si
     * un joueur a effectivement gagne la partie.
     * @return Le joueur ayant gagne cette partie.
     */
    public abstract Player getWinner();

    /**
     * Cette methode permet de retourner le board utilise pour une partie.
     * @return Le board utilise par une partie.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Cette methode permet de retourner un panel permettant de controller le
     * jeu via une GUI.
     * @return Le panel permettant de jouer au jeu.
     */
    public abstract GamePanel getPanel();

    /**
     * Cette methode permet de retourner un clone du jeu.
     * @return Un clone de ce jeu.
     */
    public abstract Game clone();
}
