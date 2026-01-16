package at.ac.hcw.hangman;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class DifficultyController {
    @FXML
    private ToggleButton easyButton;

    @FXML
    private ToggleButton mediumButton;

    @FXML
    private ToggleButton difficultButton;

    private ToggleGroup difficultyGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        easyButton.setToggleGroup(difficultyGroup);
        mediumButton.setToggleGroup(difficultyGroup);
        difficultButton.setToggleGroup(difficultyGroup);
    }

    public String getSelectedDifficulty() {
        ToggleButton selected = (ToggleButton) difficultyGroup.getSelectedToggle();
        return selected != null ? selected.getText() : null;
    }
}
