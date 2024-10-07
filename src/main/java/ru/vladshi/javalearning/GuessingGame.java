package ru.vladshi.javalearning;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GuessingGame {

    public static void start(Scanner scanner, int numberOfWords) {
        char[] word = Word.getRandom(numberOfWords);
        int errorCount = 0;
        int guessCount = 0;
        Set<Character> checkedLettersList = new HashSet<>();
        char[] currentWord = new char[word.length];
        Arrays.fill(currentWord, '_');

        while (errorCount < 6 && guessCount < word.length) {
            HangmanPicture.print(errorCount);
            printWord(currentWord);
            printCheckedLetters(checkedLettersList);
            char letter = getValidLetterFromInput(scanner);
            if (checkedLettersList.contains(letter)) {
                System.out.println("Вы уже проверяли эту букву! Будьте внимательней!");
                continue;
            }
            boolean isWordContainLetter = false;
            for (int i = 0; i < word.length; i++) {
                if (letter == word[i] && guessCount < word.length) {
                    currentWord[i] = letter;
                    guessCount++;
                    isWordContainLetter = true;
                }
            }
            if (!isWordContainLetter) {
                errorCount++;
            }
            checkedLettersList.add(letter);
        }

        if (guessCount == word.length) {
            AsciiArt.WIN.print();
            System.out.print("Вы разгадали слово: ");
        } else {
            HangmanPicture.print(errorCount);
            AsciiArt.LOSE.print();
            System.out.print("Вы не смогли разгадать слово: ");
        }
        printWord(word, false);
        System.out.println("\n");
    }

    private static char getValidLetterFromInput(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("Введите букву и нажмите Enter: ");
            input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("Введено неверное значение(введите одну букву русского алфавита)\n");
                continue;
            }
            char letter = Character.toUpperCase(input.charAt(0));
            if (letter >= 'А' && letter <= 'Я' || letter == 'Ё')
                return letter;
            System.out.println("Введено неверное значение(используйте только буквы русского алфавита)\n");
        }
    }

    private static void printWord(char[] word) {
        for (char c : word) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private static void printWord(char[] word, boolean makeSeparated) {
        if (makeSeparated) {
            printWord(word);
        } else {
            for (char c : word) {
                System.out.print(c);
            }
        }
        System.out.println();
    }

    private static void printCheckedLetters(Set<Character> checkedLetters) {
        System.out.print("[ ");
        for (char c : checkedLetters) {
            System.out.print(c + " ");
        }
        System.out.println("] - проверенные буквы");
    }
}
