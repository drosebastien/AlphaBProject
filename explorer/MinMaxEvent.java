package explorer;

public class MinMaxEvent {
    private String message;

    public MinMaxEvent() {
        this.message = null;
    }

    public MinMaxEvent(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
