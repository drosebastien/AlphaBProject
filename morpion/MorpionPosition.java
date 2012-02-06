package morpion;

import framework.*;

public class MorpionPosition extends Position {
    private int x;
    private int y;

    public MorpionPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object obj) {
        MorpionPosition position = (MorpionPosition) obj;
        return getX() == position.getX() && getY() == position.getY();
    }

    public String toString() {
        return "x : " + x + "; y : " + y;
    }
}
