package ru.vladshi.javalearning;

import java.util.Scanner;

public class HangmanGame {

    private static String nameOfFileWithWords  = "/slova.txt";
    private final static String PLAY = "1";
    private final static String QUIT = "2";

    public static void start() {
        AsciiArt.LOGO.print();
        System.out.println("Количество слов для игры = " + WordsManager.numberOfWords);
        Scanner scanner = new Scanner(System.in);
        String userChoice = "";
        while (!userChoice.equals(QUIT)) {
            System.out.println(PLAY + ". Начать новую игру");
            System.out.println(QUIT + ". Выйти");
            System.out.print("Введите цифру пункта меню и нажмите Enter:");
            userChoice = scanner.nextLine();
            switch (userChoice) {
                case PLAY: GuessingGame.start(scanner); break;
                case QUIT: System.out.println("Игра завершена. До свидания!"); break;
                default: System.out.println("Введено неверное значение\n");
            }
        }
        scanner.close();
    }

    public static String getNameOfFileWithWords() {
        return nameOfFileWithWords;
    }

    public static void setNameOfFileWithWords(String nameOfFileWithWords) {
        HangmanGame.nameOfFileWithWords = nameOfFileWithWords;
    }
}
