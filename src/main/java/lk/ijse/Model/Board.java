package lk.ijse.Model;

import lk.ijse.dto.Piece;
import lk.ijse.dto.Winner;

import java.util.Arrays;

public class Board {
    private Piece[][] board = new Piece[3][3];

    public void setAllCellsEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    public boolean isHaveWinner() {
        for (int i = 0; i < 3; i++){
            Piece piece = board[i][0];
            if(piece == Piece.EMPTY) continue;
            if(piece == board[i][1] && piece == board[i][2]){
                return true;
            }
        }

        for (int i = 0; i < 3; i++){
            Piece piece = board[0][i];
            if(piece == Piece.EMPTY) continue;
            if(piece == board[1][i] && piece == board[2][i]){
                return true;
            }
        }

        Piece piece = board[1][1];

        if(piece == Piece.EMPTY) return false;

        if(piece == board[0][0] && piece == board[2][2]){
            return true;
        }else if(piece == board[2][0] && piece == board[0][2]){
            return true;
        }

        return false;
    }

    public Winner getWinner() {
        for (int i = 0; i < 3; i++){
            Piece piece = board[i][0];
            if(piece == Piece.EMPTY) continue;
            if(piece == board[i][1] && piece == board[i][2]){
                return new Winner(piece, i,0, i, 2);
            }
        }

        for (int i = 0; i < 3; i++){
            Piece piece = board[0][i];
            if(piece == Piece.EMPTY) continue;
            if(piece == board[1][i] && piece == board[2][i]){
                return new Winner(piece, 0,i, 2, i);
            }
        }

        Piece piece = board[1][1];

        if(piece == Piece.EMPTY) return null;

        if(piece == board[0][0] && piece == board[2][2]){
            return new Winner(piece, 0,0, 2, 2);
        }else if(piece == board[2][0] && piece == board[0][2]){
            return new Winner(piece, 2,0, 0, 2);
        }
        return null;
    }

    public void showBoard(){
        for(Piece[] array : board){
            System.out.println(Arrays.toString(array));
        }
        System.out.println();
    }

    public boolean isValidMove(int column, int row) {
        return board[column][row] == Piece.EMPTY;
    }

    public boolean isBoardHaveEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                if(board[i][j].equals(Piece.EMPTY)){
                    return true;
                }
            }
        }

        return false;
    }

    public void makeMove(int column, int row, Piece piece) {
        board[column][row] = piece;
    }
}
