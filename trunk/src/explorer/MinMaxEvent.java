package explorer;

import framework.Move;

/**
 * Cette classe permet de donner des informations pour une action effectuee
 * par l'algorithme.
 * @author Sebastien Drobisz
 */
public class MinMaxEvent {
    private String message;
    private Move move;

    /**
     * Constructeur permettant de construire un evenement.
     */
    public MinMaxEvent() {
        this.message = null;
    }

    /**
     * Constructeur permettant de construire un evenement en lui donnant un
     * message.
     * @param message Le message qui caracterise l'evenement.
     */
    public MinMaxEvent(String message) {
        this.message = message;
    }

    /**
     * Cette methode permet de modifier le message de l'evenement.
     * @param message Le nouveau message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Cette methode permet donner le mouvement realise lors de l'action
     * de l'algorithme.
     * @param move Le mouvement realise.
     */
    public void setMove(Move move) {
        this.move = move;
    }

    /**
     * Cette methode permet de retourner le mouvement realise par l'algorithme.
     * lors de la creation d'un evenement.
     * @return Le mouvement effectue.
     */
    public Move getMove() {
        return move;
    }

    /**
     * Cette methode permet de retourner le message qui caracterise
     * l'evenement.
     * @return Le message qui caracterise l'evenement.
     */
    public String getMessage() {
        return message;
    }
}
