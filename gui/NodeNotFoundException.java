package gui;

public class NodeNotFoundException extends Exception {
    public NodeNotFoundException() {
        super("Node is not found");
    }

    public NodeNotFoundException(int x, int y) {
        super("Node is not found with coordinate (" + x + ", " + y + ")");
    }
}
