package at.ac.hcw.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import at.ac.hcw.hangman.model.Difficulty;

public class SetupController {

    @FXML
    protected ToggleButton easyButton;

    @FXML
    protected ToggleButton mediumButton;

    @FXML
    protected ToggleButton difficultButton;

    private Difficulty difficulty = Difficulty.EASY;

    @FXML
    protected TextField nickname;

    @FXML
    protected void onEasyMode(ActionEvent event){
        easyButton.setDisable(true);
        mediumButton.setDisable(false);
        difficultButton.setDisable(false);
        difficulty = Difficulty.EASY;

    }

    @FXML
    protected void onMediumMode(ActionEvent event){
        easyButton.setDisable(false);
        mediumButton.setDisable(true);
        difficultButton.setDisable(false);
        difficulty = Difficulty.MEDIUM;

    }

    @FXML
    protected void onDifficultMode(ActionEvent event){
        easyButton.setDisable(false);
        mediumButton.setDisable(false);
        difficultButton.setDisable(true);
        difficulty = Difficulty.HARD;

    }

    @FXML
    protected void onStartGameClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Objects.requireNonNull(getClass().getResource("view/game-view.fxml"))
            );

            Parent root = fxmlLoader.load();

            String name = nickname.getText();
            System.out.println("Player: " + name);
            System.out.println("Difficulty: " + difficulty);

            GameController controller = fxmlLoader.getController();
            controller.setGameData( name, difficulty);


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 450);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
