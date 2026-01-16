package at.ac.hcw.hangman.model;

public class LeaderboardEntry {
    private String playerName;
    private int score;

    public static int calculateScore(
            int wordLength,
            int wrongGuesses,
            Difficulty difficulty
    ) {
        int baseScore = wordLength * 50;
        int penalty = wrongGuesses * 20;

        int multiplier = switch (difficulty) {
            case EASY -> 1;
            case MEDIUM -> 2;
            case HARD -> 3;
        };

        // if finalScore is < 0 -> set to 0
        int finalScore = Math.max(0, ((baseScore - penalty) * multiplier));

        return finalScore;
    }
}
