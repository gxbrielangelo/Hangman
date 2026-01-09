package at.ac.hcw.hangman.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class WordGenerator {
    private Map<Difficulty, List<String>> wordPool = new HashMap<>();
    private HangmanGame game;

    public WordGenerator (String folderPath) {
        File folder = new File(folderPath);
        File easyWordsFile = new File(folder, "easy.txt");
        File mediumWordsFile = new File(folder, "medium.txt");
        File hardWordsFile = new File(folder, "hard.txt");

        try {
            List<String> easyWords = Files.readAllLines(easyWordsFile.toPath());
            wordPool.put(Difficulty.EASY, easyWords);

            List<String> mediumWords = Files.readAllLines(mediumWordsFile.toPath());
            wordPool.put(Difficulty.EASY, mediumWords);

            List<String> hardWords = Files.readAllLines(hardWordsFile.toPath());
            wordPool.put(Difficulty.HARD, hardWords);
        } catch (IOException e) {
            System.out.println("Could not read File");
        }
    }

    public String getRandomWord(Difficulty difficulty) {
        List<String> words = wordPool.get(difficulty);

        if (words.isEmpty()) {
            return "JAVA";
        }

        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }
}
