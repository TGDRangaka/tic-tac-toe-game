package lk.ijse.Model;

import lk.ijse.dto.Piece;
import lk.ijse.dto.Winner;

import java.util.*;

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

    public int[] predictCell() {

        List<Integer> predictedValues = new ArrayList<>();
        List<int[]> predictedIndexes = new ArrayList<>();
        for(int column = 0; column < 3; column++){
            for(int row = 0; row < 3; row++){
                boolean isValidMove = isValidMove(column, row);

                if(isValidMove){
                    makeMove(column, row, Piece.BLUE);

                    int moves = 0;
                    int value = minmax(moves,false);
                    predictedValues.add(value);
                    predictedIndexes.add(new int[]{column, row});

                    undoMove(column, row);
                }
            }
        }

        Random r = new Random();
        int max = -11;
        int maxIndex = -1;
        int min = 11;
        int minIndex = -1;
        for (int i = 0; i < predictedValues.size(); i++){
            if(predictedValues.get(i) > max){
                max = predictedValues.get(i);
                maxIndex = i;
            }
            if(predictedValues.get(i) < min){
                min = predictedValues.get(i);
                minIndex = i;
            }
        }

        if (max == min){
            return predictedIndexes.get(r.nextInt(predictedIndexes.size()));
        }else{
            return predictedIndexes.get(maxIndex);
        }
    }

    private int minmax(int moves, boolean isMaximizer) {
        boolean isHaveWinner = isHaveWinner();

        if(isHaveWinner){
            Piece piece = getWinner().getPiece();
            if(piece == Piece.BLUE){
                return 10 + moves;
            } else if (piece == Piece.RED){
                return -10 + moves;
            }
        }
        if(moves > 9 || !isBoardHaveEmpty()){
            return 0;
        }

        if (isMaximizer){
            int maxValue = -100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++){

                    boolean isValidMove = isValidMove(i, j);
                    if(isValidMove){
                        makeMove(i, j, Piece.BLUE);

                        int value = minmax(moves++, false);
                        maxValue = value > maxValue ? value : maxValue;

                        undoMove(i, j);
                    }

                }
            }

            return maxValue;
        }else{
            int minValue = 100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++){

                    boolean isValidMove = isValidMove(i, j);
                    if(isValidMove){
                        makeMove(i, j, Piece.RED);

                        int value = minmax(moves++, true);
                        minValue = value < minValue ? value : minValue;

                        undoMove(i, j);
                    }

                }
            }

            return minValue;
        }
    }

    public void undoMove(int column, int row) {
        board[column][row] = Piece.EMPTY;
    }
}
