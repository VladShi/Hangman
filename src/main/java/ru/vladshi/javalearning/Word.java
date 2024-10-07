package ru.vladshi.javalearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Word {

    private static Path pathToFileWithWords;
    public static final int numberOfWords;

    private final char[] wordToGuess;
    private final char[] maskedWord;
    public boolean isGuessed;
    public final int length;

    static {
        try {
            pathToFileWithWords = Paths.get(
                    (Objects.requireNonNull(Word.class.getResource("/slova.txt"))).toURI()
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("Не найден файл содержащий базу слов");
            System.exit(1);
        }
        numberOfWords = Word.count();
    }

    public Word() {
        this.wordToGuess = getRandom();
        this.length = wordToGuess.length;
        this.maskedWord = new char[length];
        Arrays.fill(maskedWord, '_');
        this.isGuessed = false;
    }

    private static int count() {
        int wordsQuantity = 0;
        try (BufferedReader br = Files.newBufferedReader(pathToFileWithWords, StandardCharsets.UTF_8)) {
            while (br.readLine() != null) {
                wordsQuantity++;
            }
            if (wordsQuantity == 0) {
                System.out.println("Файл со списком слов для игры пустой или содержит некорректные данные");
                System.exit(1);
            }
            return wordsQuantity;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static char[] getRandom() {
        int randomLineNumber = (int) (Math.random() * numberOfWords);
        try (BufferedReader br = Files.newBufferedReader(pathToFileWithWords, StandardCharsets.UTF_8)) {
            for (int i = 0; i < randomLineNumber; i++) {
                br.readLine();
            }
            String randomLine = br.readLine();
            if (isValidWord(randomLine))
                return randomLine.toCharArray();
            else {
                System.out.println("Некорректное значение в базе слов: " + randomLine);
                System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("Не найден файл содержащий базу слов");
            System.exit(1);
        }
        return null;
    }

    private static boolean isValidWord(String str) {
        for (char c : str.toCharArray()) {
            if (!(c >= 'А' && c <= 'Я' || c == 'Ё'))
                return false;
        }
        return true;
    }

    public int unmaskingAndGetNumberOfOccurrences(char letter) {
        int count = 0;
        for (int i = 0; i < this.length; i++) {
            if (letter == this.wordToGuess[i]) {
                this.maskedWord[i] = letter;
                count++;
            }
        }
        return count;
    }

    public void printMasked() {
        for (char c : this.maskedWord) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public void print() {
        for (char c : this.wordToGuess) {
            System.out.print(c);
        }
        System.out.println();
    }
}
