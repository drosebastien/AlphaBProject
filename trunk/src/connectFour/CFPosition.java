package connectFour;

import framework.*;

public class CFPosition extends Position {
    private int x;
    private int y;

    public CFPosition(int x) {
        this.x = x;
        y = -1;
    }

    public CFPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean yIsSet() {
        return y >= 0;
    }

    public boolean equals(Object obj) {
        CFPosition position = (CFPosition) obj;
        return getX() == position.getX();
    }

    public String toString() {
        return "ColumnIndex : " + x;
    }
}
