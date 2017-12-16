package de.adventofcode.chrisgw.day15;

public class DuelingGenerator {

    public static final long DIVIDER = 2147483647;

    private long factor;
    private long previousValue;


    public DuelingGenerator(long factor, long previousValue) {
        this.factor = factor;
        this.previousValue = previousValue;
    }

    public static DuelingGenerator newDuelingGeneratorA(long startValue) {
        return new DuelingGenerator(16807, startValue);
    }

    public static DuelingGenerator newDuelingGeneratorB(long startValue) {
        return new DuelingGenerator(48271, startValue);
    }


    public int generateNextValue() {
        long product = previousValue * factor;
        int reminder = (int) (product % DIVIDER);
        this.previousValue = reminder;
        return reminder;
    }


    public long getFactor() {
        return factor;
    }

    public long getPreviousValue() {
        return previousValue;
    }

}
