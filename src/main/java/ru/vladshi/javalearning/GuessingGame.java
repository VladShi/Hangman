package ru.vladshi.javalearning;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GuessingGame {

    private final int MAX_ERRORS_ALLOWED = HangmanPicture.numberOfPictures - 1;
    private int errorCount;
    private int guessCount;
    private final Set<Character> checkedLettersList;
    private final Word word;

    public GuessingGame() {
        this.word = new Word(WordsManager.getRandomWord());
        this.checkedLettersList = new HashSet<>();
        this.guessCount = 0;
        this.errorCount = 0;
    }
    
    public void start(Scanner scanner) {
        while (!(word.isGuessed || isManHanged())) {
            printHangman();
            printMaskedWord();
            printCheckedLetters();
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
            printHangman();
            AsciiArt.LOSE.print();
            System.out.print("Вы не смогли разгадать слово: ");
        }
        printWord();
        System.out.println();
    }

    private boolean isManHanged() {
        return errorCount >= MAX_ERRORS_ALLOWED;
    }

    private char getValidLetterFromInput(Scanner scanner) {
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

    private void printCheckedLetters() {
        System.out.print("[ ");
        for (char c : checkedLettersList) {
            System.out.print(c + " ");
        }
        System.out.println("] - проверенные буквы");
    }

    private void printWord() {
        for (char c : word.getText()) {
            System.out.print(c);
        }
        System.out.println();
    }

    private void printMaskedWord() {
        for (char c : word.getMask()) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private void printHangman() {
        System.out.println(HangmanPicture.get(errorCount));
    }
}
