package ru.vladshi.javalearning;

public class Main {
    public static void main(String[] args) {
        HangmanGame.setNameOfFileWithWords("/slova.txt");
        HangmanGame.start();
    }
}