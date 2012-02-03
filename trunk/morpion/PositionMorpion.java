package morpion;

import framework.*;

public class PositionMorpion extends Position {
    private int x;
    private int y;

    public PositionMorpion(int x, int y) {
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
        PositionMorpion position = (PositionMorpion) obj;
        return getX() == position.getX() && getY() == position.getY();
    }

    public String toString() {
        return "x : " + x + "; y : " + y;
    }
}
