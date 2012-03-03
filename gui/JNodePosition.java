package gui;

import java.util.ArrayList;

public class JNodePosition {
    private int x;
    private int y;
    private ArrayList<Integer> treePos;

    public JNodePosition(int x, int y, ArrayList<Integer> treePos) {
        this.x = x;
        this.y = y;
        this.treePos = treePos;
    }

    public ArrayList<Integer> getTreePos() {
        return treePos;
    }

    public boolean isIn(int x1, int y1, int x2, int y2) {
        return x1 <= x && x <= x2 && y1 <= y && y <= y2;
    }

    public String toString() {
        String line = "x : " + x + ", y ; " + y + "\t|";
        for(int i = 0; i < treePos.size(); i++) {
            line += treePos.get(i) + "|";
        }

        return line;
    }
}
