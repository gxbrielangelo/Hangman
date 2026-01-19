package at.ac.hcw.hangman;

import at.ac.hcw.hangman.model.Difficulty;
import at.ac.hcw.hangman.model.HangmanGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import static at.ac.hcw.hangman.WordGiver.getWord;

public class GameController {

    @FXML
    private Pane gamewin;

    @FXML
    private Pane gamelost;

    @FXML
    private Pane pausemenu;

    @FXML
    private Label displayWord;

    @FXML
    private ImageView live1;
    @FXML
    private ImageView live2;
    @FXML
    private ImageView live3;
    @FXML
    private ImageView live4;
    @FXML
    private ImageView live5;
    @FXML
    private ImageView live6;

    @FXML
    private ImageView gallows;

    @FXML
    private Label correctWord;


    @FXML
    private Button hintButton;
    @FXML
    private GridPane letterGrid;



    private HangmanGame game;
    private Difficulty difficulty;
    private String player;
    private String word;



    public void setGameData(String player,Difficulty difficulty) {
        this.player = player;
        this.difficulty = difficulty;

        int multiplier = switch (difficulty) {
            case EASY -> 1;
            case MEDIUM -> 2;
            case HARD -> 3;
        };
        word = getWord(multiplier);

        this.game = new HangmanGame(this.word,this.difficulty);
        System.out.println("Word loaded: " + this.word);
        System.out.println("Word loaded: " + game.getDisplayWord());

        displayWord.setText(game.getDisplayWord());
        correctWord.setText(game.getSecretWord());

    }


    @FXML
    protected void openRanking(ActionEvent event) {
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

    @FXML
    protected void onPauseButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Objects.requireNonNull(getClass().getResource("view/pause-view.fxml"))
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

    @FXML
    protected void restartButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("view/setup-view.fxml") // relative path to controller
            );

            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace(); // always print exceptions to debug
        }
    }
    @FXML
    protected void continueButton(ActionEvent event) {
        pausemenu.setVisible(false);
        //pausemenu.setManaged(false);
    }

    @FXML
    protected void pauseButton(ActionEvent event) {
        pausemenu.setVisible(true);
        //pausemenu.setManaged(false);
    }

    @FXML
    public void quitButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleHintButton() {
        hintButton.setText("!");

        Button random = (Button) letterGrid.getChildren().get(0);

        // get all letters that are not in the secret word
        List<Node> missingLetters = new ArrayList<>();
        for (Node node : letterGrid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;

                if (game.getNotIncludedLetters().contains(button.getText().toLowerCase().charAt(0))) {
                    missingLetters.add(node);
                }
            }
        }

        // get all letters that havent been disabled from all letters that are not in the secret word
        List<Node> enabledLetters = new ArrayList<>();
        for (Node node : missingLetters) {
            if (!node.isDisabled()) {
                enabledLetters.add(node);
            }
        }

        // randomize list of enabled Letters
        Collections.shuffle(enabledLetters);

        // get first three elements of randomized list of letters
        List<Node> randomLetters = enabledLetters.subList(0,3);

        // disable all elements (3 elements) of randomized list of letters
        for (Node node : randomLetters) {
            node.setDisable(true);
        }

        // disable hint button
        hintButton.setDisable(true);
    }

    @FXML
    private void handleLetterClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String letter = clickedButton.getText();

        System.out.println("Clicked: " + letter.charAt(0));
        clickedButton.setDisable(true);

        boolean guess = game.guess(letter.charAt(0));

        if (guess){
            // letter is in word
            // Change display word
            displayWord.setText(game.getDisplayWord());

        }
        else {
            // letter is not in word
            int lives = game.getRemainingLives();
            Image image;
            switch (lives) {
                case 5:
                    live1.setVisible(false);
                    image = new Image(
                            Objects.requireNonNull(getClass().getResourceAsStream("/at/ac/hcw/hangman/view/images/2.png"))
                    );
                    gallows.setImage(image);
                    break;

                case 4:
                    live2.setVisible(false);
                    image = new Image(
                            Objects.requireNonNull(getClass().getResourceAsStream("/at/ac/hcw/hangman/view/images/3.png"))
                    );
                    gallows.setImage(image);
                    break;

                case 3:
                    live3.setVisible(false);
                    image = new Image(
                            Objects.requireNonNull(getClass().getResourceAsStream("/at/ac/hcw/hangman/view/images/4.png"))
                    );
                    gallows.setImage(image);
                    break;

                case 2:
                    live4.setVisible(false);
                    image = new Image(
                            Objects.requireNonNull(getClass().getResourceAsStream("/at/ac/hcw/hangman/view/images/5.png"))
                    );
                    gallows.setImage(image);
                    break;

                case 1:
                    live5.setVisible(false);
                    image = new Image(
                            Objects.requireNonNull(getClass().getResourceAsStream("/at/ac/hcw/hangman/view/images/6.png"))
                    );
                    gallows.setImage(image);
                    break;

                case 0:
                    live6.setVisible(false);
                    image = new Image(
                            Objects.requireNonNull(getClass().getResourceAsStream("/at/ac/hcw/hangman/view/images/7.png"))
                    );
                    gallows.setImage(image);
                    gamelost.setVisible(true);
                    break;

            }
        }
        if(game.isWon()) {
            gamewin.setVisible(true);

            int score = game.calculateScore();
            SaveScore.saveScore(player, score);
        }
    }
}
