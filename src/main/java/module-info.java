module at.ac.hcw.hangman {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.ac.hcw.hangman to javafx.fxml;
    exports at.ac.hcw.hangman;
    exports at.ac.hcw.hangman.model;
    opens at.ac.hcw.hangman.model to javafx.fxml;
}