package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainWindowFormController {

    @FXML
    private AnchorPane root;

    @FXML
    void btnPlayOnAction(ActionEvent event) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/game_window_form.fxml"));
        root.getChildren().setAll(node);
    }

}
