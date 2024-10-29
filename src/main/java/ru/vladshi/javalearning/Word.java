package ru.vladshi.javalearning;

import java.util.Arrays;

public class Word {

    private final char[] text;
    private final char[] mask;
    public boolean isGuessed;
    public final int length;

    public Word(char[] text) {
        this.text = text;
        this.length = text.length;
        this.mask = new char[length];
        Arrays.fill(mask, '_');
        this.isGuessed = false;
    }

    public int unmaskAndGetNumberOfOccurrences(char letter) {
        int count = 0;
        for (int i = 0; i < this.length; i++) {
            if (letter == this.text[i]) {
                this.mask[i] = letter;
                count++;
            }
        }
        return count;
    }

    public char[] getText() {
        return text;
    }

    public char[] getMask() {
        return mask;
    }
}
