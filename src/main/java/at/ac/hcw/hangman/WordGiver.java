package at.ac.hcw.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordGiver {
    public static String getWord(int difficulty) {
        String filename;
        switch (difficulty) {
            case 1: filename = "/at/ac/hcw/hangman/view/easy.txt"; break;
            case 2: filename = "/at/ac/hcw/hangman/view/medium.txt"; break;
            case 3: filename = "/at/ac/hcw/hangman/view/hard.txt"; break;
            default: throw new IllegalArgumentException("Invalid difficulty");
        }

        List<String> words = new ArrayList<>();
        InputStream is = WordGiver.class.getResourceAsStream(filename);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }
}