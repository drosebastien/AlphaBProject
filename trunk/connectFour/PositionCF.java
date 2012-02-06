package connectFour;

import framework.*;

public class PositionCF extends Position {
    private int x;
    private int y;

    public PositionCF(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public boolean equals(Object obj) {
        PositionCF position = (PositionCF) obj;
        return getX() == position.getX();
    }

    public String toString() {
        return "ColumnIndex : " + x;
    }
}
