package framework;

import java.util.concurrent.Semaphore;

/**
 * Cette classe permet de creer un joueur.
 * @author Sebastien Drobisz
 */
public abstract class Player implements Runnable {
    private String name;
    public Piece piece;
    private int id;
    private boolean isStopped;
    private Move playingMove;
    private boolean isEnd;
    private boolean isFinalDecision;
    protected Game game;
    protected Semaphore sem;

    /**
     * Ce constructeur permet de creer un nouveau joueur en lui donnant un nom
     * et un numero identifiant.
     * @param name Le nom du joueur ajoute.
     * @param id L'identifiant du joueur ajoute.
     */
    public Player(String name, int id) {
        this.name = name;
        this.piece = null;
        this.id = id;
        this.isStopped = false;
        this.isFinalDecision = false;
        this.isEnd = false;
        playingMove = null;
    }

    /**
     * Cette methode permet de fournir un semaphore a un joueur pour que celui-
     * ci ne puisse jouer avant que ce soit a son tour de jouer.
     */
    public void setSemaphore(Semaphore sem) {
        this.sem = sem;
    }

    /**
     * Permet de donner un jeton au joueur.
     * @param piece Le jeton correspondant au joueur.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Cette methode permet de donner le jeu au joueur pour que celui-ci
     * puisse acceder a toute information pouvant lui etre utile.
     * @param game Le jeu auquel doit jouer le joueur.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Cette methode permet de faire jouer le joueur (utilise si joueur IA).
     */
    public abstract void run();

    /**
     * Permet de savoir si un joueur (IA) a deja choisi un premier coup. Cela
     * est utile si un joueur a deja un coup favoris mais qu'il continue
     * l'evaluation dans le but d'en trouver un meilleur.
     * (Methode utilise avec l'iterative deepening search pour pouvoir exploiter
     * entierement le temps imparti a un joueur.)
     * @return true si un coup favoris temporaire est existant, false sinon.
     */
    public boolean hasMove() {
        return playingMove != null;
    }

    /**
     * Cette methode permet de selectionner un coup favoris temporaire.
     * @see #hasMove()
     * @param move Le mouvement favoris temporaire.
     */
    protected void setPlayingMove(Move move) {
        playingMove = move;
    }

    /**
     * Permet de dire si le coup temporaire selectionne est en fait la
     * decision finale du joueur.
     */
    protected void setIsFinalDecision() {
        isFinalDecision = true;
    }

    /**
     * Permet de prevenir le joueur s'il doit arreter d'evaluer.
     * @param isEnd true s'il doit stopper, false sinon.
     */
    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * permet d'avoir le mouvement temporaire ou final selectionne par le joueur
     * @return Le mouvement selectionne par le joueur.
     */
    public Move getMove() {
        isFinalDecision = false;
        Move tmp = playingMove;
        playingMove = null;
        return tmp;
    }

    /**
     * Permet de savoir si le coup selectionne par le joueur est sa decision
     * finale ou non.
     * @return true si c'est sa decision finale, false sinon.
     */
    public boolean isFinalDecision() {
        return isFinalDecision;
    }

    /**
     * Permet de savoir si l'ia a termine son evaluation ou non.
     * @param isStopped true si oui, false sinon.
     */
    public void setIsStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }

    /**
     * Cette methode permet de retourner l'id associe au joueur.
     * @return L'identifiant du joueur.
     */
    public int getId() {
        return id;
    }

    /**
     * Cette methode permet de retourner le nom du joueur.
     * @return Le nom du joueur.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de retourner le jeton attribue au joueur.
     * @return Le jeton associe au joueur.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Cette methode permet de faire une copie du joueur.
     * @return La copie du joueur.
     */
    public abstract Player clone();

    /**
     * Cette methode permet de retourner une chaine de caractere decrivant
     * Le joueur.
     * @return La chaine de caractere decrivant le joueur.
     */
    public String toString() {
        return "Player's name : " + name + "; id : " + id;
    }
}
