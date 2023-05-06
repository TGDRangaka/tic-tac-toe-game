package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Model.Service;
import lk.ijse.dto.Piece;
import lk.ijse.dto.Winner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GameWindowFormController implements Initializable {
    @FXML
    private AnchorPane root;

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

    private Piece[][] board = new Piece[3][3];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImageViewList();
        setAllCellsEmpty();
        setImgOnMouseClicked();
    }

    private void setImageViewList() {
        imageViews.add(img1); imageViews.add(img2); imageViews.add(img3);
        imageViews.add(img4); imageViews.add(img5); imageViews.add(img6);
        imageViews.add(img7); imageViews.add(img8); imageViews.add(img9);
    }

    private void setAllCellsEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    private void setImgOnMouseClicked() {
        for(ImageView imageView : imageViews){
            imageView.setOnMouseClicked((e) -> {

                runGame(imageView);

            });
        }
    }

    private void runGame(ImageView imageView) {
        boolean isBoardHaveEmpty = Service.isBoardHaveEmpty(board);

        int[] indexes = Service.getClickedCellIndex(imageViews, imageView);
        int column = indexes[0];
        int row = indexes[1];
        System.out.println("(" + column +"," + row + ")");

        boolean isValidMove = Service.isValidMove(column, row, board);

        if (isValidMove){
            imageView.setImage(new Image("/img/circle.png"));
            board[column][row] = Piece.RED;
        }

        boolean isHaveWinner = Service.isHaveWinner(board);
        if(isHaveWinner){
            Winner winner = Service.getWinner(board);
        }

        System.out.println("isHaveWinner - " + isHaveWinner);

        showBoard();

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/main_window_form.fxml"));
        root.getChildren().setAll(node);
    }

    private void showBoard(){
        for(Piece[] array : board){
            System.out.println(Arrays.toString(array));
        }
        System.out.println();
    }
}
