package ru.vladshi.javalearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

public class WordsManager {

    private static Path pathToFileWithWords;
    public static final int numberOfWords;

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
        numberOfWords = getNumberOfWords();
    }

    private static int getNumberOfWords() {
        int wordsQuantity = 0;
        try (BufferedReader br = Files.newBufferedReader(pathToFileWithWords, StandardCharsets.UTF_8)) {
            while (br.readLine() != null && !br.readLine().isBlank()) {
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

    public static char[] getRandomWord() {
        int randomLineNumber = new Random().nextInt(numberOfWords);
        String randomLine = "";
        char[] randomWord = null;
        try (BufferedReader br = Files.newBufferedReader(pathToFileWithWords, StandardCharsets.UTF_8)) {
            for (int i = 0; i < randomLineNumber; i++) {
                br.readLine();
            }
            randomLine = br.readLine();
            if (isValidWord(randomLine)) {
                randomWord = randomLine.toCharArray();
            }
        } catch (IOException e) {
            System.out.println("Не найден файл содержащий базу слов");
            System.exit(1);
        }
        if (randomWord == null) {
            System.out.println("Некорректное значение в базе слов: " + randomLine);
            System.exit(1);
        }
        return randomWord;
    }

    public static boolean isValidLetter(char c) {
        return c >= 'А' && c <= 'Я' || c == 'Ё';
    }

    private static boolean isValidWord(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!isValidLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
