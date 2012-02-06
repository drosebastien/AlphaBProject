package connectFour;

import framework.*;

public class CFBoard extends Board {
    private CFPiece[][] board;

    public CFBoard() {
        board = new CFPiece[7][6];
    }

    public CFBoard(int width, int height) {
        board = new CFPiece[width][height];
    }

    public int getWidth() {
        return board.length;
    }

    public int getHeight() {
        return board[0].length;
    }

    /**
        Retourne vrai si la colonne identifiée par x possède encore au moins
        une case vide. Sinon retourne faux.
    */
    public boolean isFree(Position pos) {
        return firstAvailableLine(((CFPosition) pos).getX()) != getHeight();
    }

    public int firstAvailableLine(int x) {
        int firstLine = getHeight();

        for(int i = getHeight() - 1; i >= 0; i--) {
            if(board[x][i] == null) {
                firstLine = i;
            }
            else {
                return firstLine;
            }
        }

        return firstLine;
    }

    public void placePiece(Position pos, Piece piece) {
        int columnIndex = ((CFPosition) pos).getX();

        board[columnIndex][firstAvailableLine(columnIndex)] = (CFPiece) piece;
    }

    public void removePiece(Position pos) {
        int columnIndex = ((CFPosition) pos).getX();

        int lineIndex = firstAvailableLine(columnIndex);
        board[columnIndex][lineIndex] = null;
    }

    public Piece getPiece(Position pos) {
        int columnIndex = ((CFPosition) pos).getX();
        int lineIndex = firstAvailableLine(columnIndex);

        return board[columnIndex][lineIndex];
    }

    public Board clone() {
        CFBoard boardCopy = new CFBoard(board.length, board[0].length);

        for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getHeight(); j++) {
                if(board[i][j] != null) {
                    boardCopy.board[i][j] = (CFPiece) board[i][j].clone();
                }
            }
        }

        return boardCopy;
    }

    public String toString() {
        String line = "";
        for(int i = getHeight() - 1; i >= 0 ; i--) {
            line += "|";
            for(int j = 0; j < getWidth(); j++) {
                if(board[j][i] == null) {
                    line += " |";
                }
                else if(board[j][i].getId() == 0) {
                    line += "o|";
                }
                else {
                    line += "x|";
                }
            }
            line += "\n";
        }

        return line;
    }
}
