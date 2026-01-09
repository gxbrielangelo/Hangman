package at.ac.hcw.hangman.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Integer> scores = new ArrayList<>();
    private int highscore;
    private int gamesWon;

    public Player (String name) {
        this.name = name;
        this.highscore = 0;
        this.gamesWon = 0;
    }

    public Player (String name, List<Integer> scores, int highscore, int gamesWon) {
        this.name = name;
        this.scores = scores;
        this.highscore = highscore;
        this.gamesWon = gamesWon;
    }

    public void recordWin (int score) {
        this.gamesWon++;
        this.scores.add(score);
        if (score > this.highscore) {
            this.highscore = score;
        }
    }

    public String getName () { return this.name; }
    public int getHighscore () { return this.highscore; }
    public int getGamesWon () { return this.gamesWon; }
    public List<Integer> getScores () { return this.scores; }

    @Override
    public String toString() {
        return String.format("Player[Name: %s, Wins %d, Top Score: %d]",
                                    name, gamesWon, highscore);
    }
}
