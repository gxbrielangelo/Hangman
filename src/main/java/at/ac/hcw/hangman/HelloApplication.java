package at.ac.hcw.hangman;

import at.ac.hcw.hangman.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// testing purposes
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String words = WordGiver.getWord(1);
        System.out.println("Word: " + words);
        SaveScore.saveScore("Ben", 150);
        SaveScore.saveScore("Ben", 250);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        /* Setup Phase
        WordGenerator wordGenerator = new WordGenerator();
        wordGenerator.putWord(Difficulty.EASY, "HELLO");
        wordGenerator.putWord(Difficulty.EASY, "WORLD");
        wordGenerator.putWord(Difficulty.EASY, "HANGMAN");

        // Playing Phase
        String word = wordGenerator.getRandomWord(Difficulty.EASY);
        HangmanGame game = new HangmanGame(word, Difficulty.EASY);
        System.out.println("The word is " + word);
        Scanner sc = new Scanner(System.in);

        // Game Loop
        while (!game.isWon()) {
            String c = sc.next();
            System.out.println(c.charAt(0));
            System.out.println(game.guess(c.charAt(0)));
            System.out.println(game.getDisplayWord());
            if (game.getWrongGuesses() == 1) {
                System.out.println("You have made " + game.getWrongGuesses() + " wrong guess");
            } else {
                System.out.println("You have made " + game.getWrongGuesses() + " wrong guesses");
            }
            System.out.println("You have only " + game.getRemainingLives() + " left");

            // Check Losing Condition
            if (game.isLost()) {
                sc.close();
                stage.close();
                break;
            }
        }

         */
    }
}
