package connectFour;

import framework.*;

public class CFPosition extends Position {
    private int x;
    private int y;

    public CFPosition(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public boolean equals(Object obj) {
        CFPosition position = (CFPosition) obj;
        return getX() == position.getX();
    }

    public String toString() {
        return "ColumnIndex : " + x;
    }
}
