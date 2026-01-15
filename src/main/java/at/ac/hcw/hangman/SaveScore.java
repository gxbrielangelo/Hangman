package at.ac.hcw.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SaveScore {
    private static final String RANKING_FILE = "src/main/resources/at/ac/hcw/hangman/view/ranking.txt";

    public static void saveScore(String name, int score) {
        if (doesNameExists(name)) {
            return;
        }

        String content = name + " " + score + System.lineSeparator();
        try {
            Path path = Paths.get(RANKING_FILE);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            if (Files.exists(path) && Files.size(path) > 0) {
                String currentContent = Files.readString(path);
                if (!currentContent.endsWith(System.lineSeparator())) {
                    content = System.lineSeparator() + content;
                }
            }

            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean doesNameExists(String name) {
        try {
            Path path = Paths.get(RANKING_FILE);
            if (!Files.exists(path)) {
                return false;
            }
            java.util.List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.trim().split(" ");
                if (parts.length > 0 && parts[0].equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}