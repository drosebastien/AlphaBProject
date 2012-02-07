package morpion;

import framework.*;

public class MorpionBoard extends Board {
    private MorpionPiece[][] board;

    public MorpionBoard() {
        board = new MorpionPiece[3][3];
    }

    public int getWidth() {
        return board.length;
    }

    public boolean isFree(Position pos) {
        MorpionPosition position = (MorpionPosition) pos;
        return board[position.getX()][position.getY()] == null;
    }

    public void placePiece(Position pos, Piece piece) {
        MorpionPosition position = (MorpionPosition) pos;
        board[position.getX()][position.getY()] = (MorpionPiece) piece;
    }

    public void removePiece(Position pos) {
        MorpionPosition position = (MorpionPosition) pos;
        board[position.getX()][position.getY()] = null;
    }

    public Piece getPiece(Position pos) {
        MorpionPosition position = (MorpionPosition) pos;
        return board[position.getX()][position.getY()];
    }

    /**
     * Commentaire rajouté uniquement pour tester le svn
     */
    public Board clone() {
        MorpionBoard boardCopy = new MorpionBoard();

        for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getWidth(); j++) {
                if(board[i][j] != null) {
                    boardCopy.board[i][j] = (MorpionPiece) board[i][j].clone();
                }
            }
        }

        return boardCopy;
    }

    public String toString() {
        String line = "";
        for(int i = getWidth() - 1; i >= 0; i--) {
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
