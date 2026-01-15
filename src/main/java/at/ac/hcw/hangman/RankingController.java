package at.ac.hcw.hangman;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RankingController {
    @FXML
    private TableView<PlayerScore> rankingTable;

    @FXML
    private TableColumn<PlayerScore, String> playerColumn;

    @FXML
    private TableColumn<PlayerScore, Integer> scoreColumn;

    @FXML
    private TableColumn<PlayerScore, Integer> rankColumn;

    @FXML
    public void initialize() {
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("player"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        List<String> rankingStrings = GetRanking.getRanking();

        ObservableList<PlayerScore> data = FXCollections.observableArrayList();

        for (String entry : rankingStrings) {
            String[] parts = entry.split(" ");
            if (parts.length == 2) {
                try {
                    String player = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    data.add(new PlayerScore(player, score));
                } catch (NumberFormatException ignored) { }
            }
        }

        rankingTable.setItems(data);

        rankColumn.setCellValueFactory(cellData -> {
            PlayerScore player = cellData.getValue();
            int index = rankingTable.getItems().indexOf(player) + 1;
            return new javafx.beans.property.SimpleIntegerProperty(index).asObject();
        });
    }

    public static class PlayerScore {
        private final String player;
        private final Integer score;

        public PlayerScore(String player, Integer score) {
            this.player = player;
            this.score = score;
        }

        public String getPlayer() {
            return player;
        }

        public Integer getScore() {
            return score;
        }
    }


    @FXML
    protected void onExitClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Objects.requireNonNull(getClass().getResource("view/exit-view.fxml"))
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