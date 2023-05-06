package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Winner {
    private Piece piece;
    private int firstPieceColumn;
    private int firstPieceRow;
    private int lastPieceColumn;
    private int getLastPieceRow;
}
