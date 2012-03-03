package gui;

public class JTreeMargin {
    private int centerOfRoot;
    private int rightMargin;

    public JTreeMargin(int centerOfRoot, int rightMargin) {
        this.centerOfRoot = centerOfRoot;
        this.rightMargin = rightMargin;
    }

    public int getCenterOfRoot() {
        return centerOfRoot;
    }

    public int getRightMargin() {
        return rightMargin;
    }
}
