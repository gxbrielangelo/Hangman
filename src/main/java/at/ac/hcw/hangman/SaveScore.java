package at.ac.hcw.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaveScore {
    private static final String RANKING_FILE = "src/main/resources/at/ac/hcw/hangman/view/ranking.txt";

    public static void saveScore(String name, int score) {
        Path path = Paths.get(RANKING_FILE);

        Map<String, Integer> scores = new HashMap<>();

        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    int lastSpace = line.lastIndexOf(' ');
                    if (lastSpace == -1) {
                        continue;
                    }

                    String existingName = line.substring(0, lastSpace).trim();
                    String scorePart = line.substring(lastSpace + 1).trim();

                    try {
                        int existingScore = Integer.parseInt(scorePart);
                        scores.put(existingName, existingScore);
                    } catch (NumberFormatException ignored) {
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scores.put(name, scores.getOrDefault(name, 0) + score);

        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            List<String> linesToWrite = scores.entrySet().stream()
                    .map(entry -> entry.getKey() + " " + entry.getValue())
                    .collect(Collectors.toList());

            Files.write(path, linesToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}