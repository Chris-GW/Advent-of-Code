package de.adventofcode.chrisgw.day01;

public enum Direction {
    LEFT('L', -1),
    RIGHT('R', 1);

    private final char code;
    private final int sign;


    Direction(char code, int sign) {
        this.code = code;
        this.sign = sign;
    }

    public static Direction fromCode(char code) {
        for (Direction direction : Direction.values()) {
            if (direction.code == code) {
                return direction;
            }
        }
        return null;
    }


    public char code() {
        return code;
    }

    public int sign() {
        return sign;
    }

}
