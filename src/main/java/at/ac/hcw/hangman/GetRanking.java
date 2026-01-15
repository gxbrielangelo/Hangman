package at.ac.hcw.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GetRanking {

    public static List<String> getRanking() {
        List<UserScore> scores = new ArrayList<>();

        Path path = Paths.get("src/main/resources/at/ac/hcw/hangman/view/ranking.txt");
        if (!Files.exists(path)) {
            System.err.println("Ranking file not found: " + path);
            return new ArrayList<>();
        }

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    try {
                        int score = Integer.parseInt(parts[1]);
                        scores.add(new UserScore(name, score));
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scores.sort(Comparator.comparingInt(UserScore::getScore).reversed());

        List<String> ranking = new ArrayList<>();
        for (UserScore us : scores) {
            ranking.add(us.getName() + " " + us.getScore());
        }

        return ranking;
    }

    public static class UserScore {
        private final String name;
        private final int score;

        public UserScore(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}