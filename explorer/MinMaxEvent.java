package explorer;

import framework.Move;

public class MinMaxEvent {
    private String message;
    private Move move;

    public MinMaxEvent() {
        this.message = null;
    }

    public MinMaxEvent(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public String getMessage() {
        return message;
    }
}
