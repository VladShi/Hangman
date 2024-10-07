package ru.vladshi.javalearning;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GuessingGame {

    public static void start(Scanner scanner) {
        Word word = new Word();
        int errorCount = 0;
        int guessCount = 0;
        Set<Character> checkedLettersList = new HashSet<>();

        while (errorCount < 6 && !word.isGuessed) {
            HangmanPicture.print(errorCount);
            word.printMasked();
            printCheckedLetters(checkedLettersList);
            char letter = getValidLetterFromInput(scanner);
            if (checkedLettersList.contains(letter)) {
                System.out.println("Вы уже проверяли эту букву! Будьте внимательней!");
                continue;
            }
            int numberOfLetterOccurrences = word.unmaskingAndGetNumberOfOccurrences(letter);
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
            HangmanPicture.print(errorCount);
            AsciiArt.LOSE.print();
            System.out.print("Вы не смогли разгадать слово: ");
        }
        word.print();
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
            if (letter >= 'А' && letter <= 'Я' || letter == 'Ё')
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
}
