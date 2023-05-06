package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lk.ijse.Model.Board;
import lk.ijse.Model.Service;
import lk.ijse.dto.Piece;
import lk.ijse.dto.Winner;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameWindowFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private GridPane gridBoard;

    @FXML
    private ImageView img8;

    @FXML
    private ImageView img9;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img7;

    private List<ImageView> imageViews = new ArrayList<>();

    private Board board;
    private Winner winner = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = new Board();

        setImageViewList();
        board.setAllCellsEmpty();
        setImgOnMouseClicked();
    }

    private void setImageViewList() {
        imageViews.add(img1); imageViews.add(img2); imageViews.add(img3);
        imageViews.add(img4); imageViews.add(img5); imageViews.add(img6);
        imageViews.add(img7); imageViews.add(img8); imageViews.add(img9);
    }

    private void setImgOnMouseClicked() {
        for(ImageView imageView : imageViews){
            imageView.setOnMouseClicked((e) -> {

                runGame(imageView);
                if(winner != null) {
                    Piece piece = winner.getPiece();
                    gridBoard.setDisable(true);

                    if(piece == Piece.RED) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Winner winner chicken dinner").show();
                    }else if(piece == Piece.BLUE){
                        new Alert(Alert.AlertType.CONFIRMATION, "You Lose!!").show();
                    }else if(piece == Piece.DRAW){
                        new Alert(Alert.AlertType.CONFIRMATION, "! DRAW !").show();
                    }

                }

            });
        }
    }

    private void runGame(ImageView imageView) {
        if (!board.isBoardHaveEmpty()){
            winner = new Winner(Piece.DRAW, 0,0,0,0);
            return;
        }

        int[] indexes = Service.getClickedCellIndex(imageViews, imageView);
        int column = indexes[0];
        int row = indexes[1];
//        System.out.println("Move RED - (" + column +"," + row + ")");

        boolean isValidMove = board.isValidMove(column, row);

        if (isValidMove){
            imageView.setImage(new Image("/img/cross.png"));
            board.makeMove(column, row, Piece.RED);
        }else{
            return;
        }

        boolean isHaveWinner = board.isHaveWinner();
        if(isHaveWinner){
            winner = board.getWinner();
            System.out.println(winner.toString());
            return;
        }

        //
        if (!board.isBoardHaveEmpty()){
            winner = new Winner(Piece.DRAW, 0,0,0,0);
            return;
        }

        int[] predictColRow = board.predictCell();
        int col = predictColRow[0];
        int rw = predictColRow[1];

//        Random r = new Random();
//        boolean validMove = false;
//        while (!validMove){
//            col = r.nextInt(3);
//            rw = r.nextInt(3);
//            validMove = board.isValidMove(col, rw);
//        }
//        System.out.println("Move BLUE - (" + col +"," + rw + ")");

        board.makeMove(col, rw, Piece.BLUE);
        imageView = Service.getImageView(col, rw, imageViews);
        imageView.setImage(new Image("/img/circle.png"));

        isHaveWinner = board.isHaveWinner();
        if(isHaveWinner){
            winner = board.getWinner();
            System.out.println(winner.toString());
            return;
        }

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/main_window_form.fxml"));
        root.getChildren().setAll(node);
    }
}
