package at.ac.hcw.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    @FXML
    protected void onFinishGameClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Objects.requireNonNull(getClass().getResource("view/ranking-view.fxml"))
            );
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 450);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
