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

    public static void print(int number) {
        System.out.println(pictures[number]);
    }
}
