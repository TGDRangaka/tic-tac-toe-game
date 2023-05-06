package lk.ijse.Model;

import javafx.scene.image.ImageView;
import lk.ijse.dto.Piece;
import lk.ijse.dto.Winner;

import java.util.List;

public class Service {

    public static boolean isBoardHaveEmpty(Piece[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                if(board[i][j].equals(Piece.EMPTY)){
                    return true;
                }
            }
        }

        return false;
    }

    public static int[] getClickedCellIndex(List<ImageView> imageViews, ImageView imageView) {
        if(imageView == imageViews.get(0)){
            return new int[]{0,0};
        }else if(imageView == imageViews.get(1)){
            return new int[]{0,1};
        }else if(imageView == imageViews.get(2)){
            return new int[]{0,2};
        }else if(imageView == imageViews.get(3)){
            return new int[]{1,0};
        }else if(imageView == imageViews.get(4)){
            return new int[]{1,1};
        }else if(imageView == imageViews.get(5)){
            return new int[]{1,2};
        }else if(imageView == imageViews.get(6)){
            return new int[]{2,0};
        }else if(imageView == imageViews.get(7)){
            return new int[]{2,1};
        }else if(imageView == imageViews.get(8)){
            return new int[]{2,2};
        }
        return null;
    }

    public static boolean isValidMove(int column, int row, Piece[][] board) {
        return board[column][row] == Piece.EMPTY;
    }

    public static boolean isHaveWinner(Piece[][] board) {
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

    public static Winner getWinner(Piece[][] board) {
        return null;
    }
}
