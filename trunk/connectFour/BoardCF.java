package connectFour;

import framework.*;

public class BoardCF extends Board {
    private PieceCF[][] board;

    public BoardCF() {
        board = new PieceCF[6][7];
    }

    public BoardCF(int width, int height) {
        board = new PieceCF[width][height];
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
        return firstAvailableLine(((PositionCF) pos).getX()) != getHeight();
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
        int columnIndex = ((PositionCF) pos).getX();

        board[columnIndex][firstAvailableLine(columnIndex)] = (PieceCF) piece;
    }

    public void removePiece(Position pos) {
        int columnIndex = ((PositionCF) pos).getX();

        int lineIndex = firstAvailableLine(columnIndex);
        board[columnIndex][lineIndex] = null;
    }

    public Piece getPiece(Position pos) {
        int columnIndex = ((PositionCF) pos).getX();
        int lineIndex = firstAvailableLine(columnIndex);

        return board[columnIndex][lineIndex];
    }

    public Board clone() {
        BoardCF boardCopy = new BoardCF(board.length, board[0].length);

        for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getWidth(); j++) {
                if(board[i][j] != null) {
                    boardCopy.board[i][j] = (PieceCF) board[i][j].clone();
                }
            }
        }

        return boardCopy;
    }

    public String toString() {
        String line = "";
        for(int i = 0; i < board.length; i++) {
            line += "|";
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == null) {
                    line += " |";
                }
                else if(board[i][j].getId() == 0) {
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
