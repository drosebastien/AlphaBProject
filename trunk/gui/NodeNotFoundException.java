package gui;

public class NodeNotFoundException extends Exception {
    public NodeNotFoundException() {
        System.out.println("Node is not found");
    }

    public NodeNotFoundException(int x, int y) {
        System.out.printf("Node is not found with coordinate (%d, %d)\n", x, y);
    }
}
