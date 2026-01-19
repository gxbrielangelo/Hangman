package at.ac.hcw.hangman.model;

import java.util.HashSet;
import java.util.Set;

public class HangmanGame {
    private String secretWord;
    private Set<Character> guessedLetters;
    private int wrongGuesses;
    private Difficulty difficulty;

    public HangmanGame(String word, Difficulty difficulty) {
        this.secretWord = word.toUpperCase();
        this.difficulty = difficulty;
        this.guessedLetters = new HashSet<>();
        this.wrongGuesses = 0;
    }


    public boolean guess(char letter) {
        letter = Character.toUpperCase(letter);

        if (guessedLetters.contains(letter)) {
            // Buchstabe wurde schon geraten -> nichts tun oder Exception werfen
            return false;
        }

        guessedLetters.add(letter);

        if (secretWord.indexOf(letter) >= 0) {
            return true; // Treffer
        } else {
            wrongGuesses++;
            return false; // Fehler
        }
    }


    public String getDisplayWord() {
        StringBuilder builder = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                builder.append(c).append(" ");
            } else {
                builder.append("_ ");
            }
        }
        return builder.toString().trim();
    }

    public boolean isWon() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }

        return true;
    }


    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public Set<Character> getNotIncludedLetters() {
        Set<Character> missingLetters = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            missingLetters.add(c);
        }

        for (char c : secretWord.toLowerCase().toCharArray()) {
            missingLetters.remove(c);
        }

        return missingLetters;
    }

    public int calculateScore() {
        int wordLength = secretWord.length();

        int multiplier = switch (difficulty) {
            case EASY -> 1;
            case MEDIUM -> 2;
            case HARD -> 3;
        };

        int baseScore = wordLength * 50;
        int penalty = wrongGuesses * 20;

        // if finalScore is < 0 -> set to 0
        int finalScore = Math.max(0, ((baseScore - penalty) * multiplier));

        return finalScore;
    }

    public boolean isLost() {
        return wrongGuesses >= 6;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public int getRemainingLives() {
        return 6 - wrongGuesses;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public String getSecretWord() {
        return this.secretWord;
    }
}
