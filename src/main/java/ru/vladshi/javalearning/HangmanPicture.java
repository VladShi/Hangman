package ru.vladshi.javalearning;

public class HangmanPicture {

    private static final String[] pictures = new String[] {"""
          +---+
          |   |
              |
              |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
              |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|\\  |
         /    |
              |
        =========""", """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        ========="""};

    public static final int numberOfPictures = pictures.length;

    public static String get(int number) {
        return pictures[number];
    }
}
