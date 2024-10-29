package ru.vladshi.javalearning;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GuessingGame {

    public static void start(Scanner scanner) {
        Word word = new Word(WordsManager.getRandomWord());
        int errorCount = 0;
        int maxErrorsAllowed = HangmanPicture.numberOfPictures - 1;
        int guessCount = 0;
        Set<Character> checkedLettersList = new HashSet<>();

        while (errorCount < maxErrorsAllowed && !word.isGuessed) {
            System.out.println(HangmanPicture.get(errorCount));
            printMaskedWord(word);
            printCheckedLetters(checkedLettersList);
            char letter = getValidLetterFromInput(scanner);
            if (checkedLettersList.contains(letter)) {
                System.out.println("Вы уже проверяли эту букву! Будьте внимательней!");
                continue;
            }
            int numberOfLetterOccurrences = word.unmaskAndGetNumberOfOccurrences(letter);
            if (numberOfLetterOccurrences > 0) {
                guessCount += numberOfLetterOccurrences;
                word.isGuessed = (guessCount == word.length);
            }
            else {
                errorCount++;
            }
            checkedLettersList.add(letter);
        }

        if (word.isGuessed) {
            AsciiArt.WIN.print();
            System.out.print("Вы разгадали слово: ");
        } else {
            System.out.println(HangmanPicture.get(errorCount));
            AsciiArt.LOSE.print();
            System.out.print("Вы не смогли разгадать слово: ");
        }
        printWord(word);
        System.out.println();
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
            if (WordsManager.isValidLetter(letter))
                return letter;
            System.out.println("Введено неверное значение(используйте только буквы русского алфавита)\n");
        }
    }

    private static void printCheckedLetters(Set<Character> checkedLetters) {
        System.out.print("[ ");
        for (char c : checkedLetters) {
            System.out.print(c + " ");
        }
        System.out.println("] - проверенные буквы");
    }

    private static void printWord(Word word) {
        for (char c : word.getText()) {
            System.out.print(c);
        }
        System.out.println();
    }

    private static void printMaskedWord(Word word) {
        for (char c : word.getMask()) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
