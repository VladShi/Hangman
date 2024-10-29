package ru.vladshi.javalearning;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AsciiArt.LOGO.print();
        System.out.println("Количество слов для игры = " + WordsManager.numberOfWords);
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        while (userChoice != 2) {
            System.out.print("""
                    1. Начать новую игру
                    2. Выйти
                    
                    Введите цифру пункта меню и нажмите Enter:
                    """);
            String input = scanner.nextLine();
            try {
                userChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение\n");
                continue;
            }
            switch (userChoice) {
                case 1: GuessingGame.start(scanner); break;
                case 2: System.out.println("Игра завершена. До свидания!"); break;
                default: System.out.println("Введено неверное значение\n");
            }
        }
        scanner.close();
    }
}