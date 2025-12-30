module at.ac.hcw.hangman {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.ac.hcw.hangman to javafx.fxml;
    exports at.ac.hcw.hangman;
}