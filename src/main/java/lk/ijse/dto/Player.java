package lk.ijse.dto;

import lk.ijse.Model.Board;

public abstract class Player {
    private Piece piece;

    abstract void playRound(int column, int row, Board board);
}
