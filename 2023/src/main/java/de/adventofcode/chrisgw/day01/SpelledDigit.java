package de.adventofcode.chrisgw.day01;

public enum SpelledDigit {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

    private final int digit;

    SpelledDigit(int digit) {
        this.digit = digit;
    }

    public int getDigit() {
        return digit;
    }

}
