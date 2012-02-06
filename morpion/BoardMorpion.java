package morpion;

import framework.*;

public class BoardMorpion extends Board {
    private PieceMorpion[][] board;

    public BoardMorpion() {
        board = new PieceMorpion[3][3];
    }

    public int getWidth() {
        return board.length;
    }

    public boolean isFree(Position pos) {
        PositionMorpion position = (PositionMorpion) pos;
        return board[position.getX()][position.getY()] == null;
    }

    public void placePiece(Position pos, Piece piece) {
        PositionMorpion position = (PositionMorpion) pos;
        board[position.getX()][position.getY()] = (PieceMorpion) piece;
    }

    public void removePiece(Position pos) {
        PositionMorpion position = (PositionMorpion) pos;
        board[position.getX()][position.getY()] = null;
    }

    public Piece getPiece(Position pos) {
        PositionMorpion position = (PositionMorpion) pos;
        return board[position.getX()][position.getY()];
    }

    public Board clone() {
        BoardMorpion boardCopy = new BoardMorpion();

        for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getWidth(); j++) {
                if(board[i][j] != null) {
                    boardCopy.board[i][j] = (PieceMorpion) board[i][j].clone();
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
