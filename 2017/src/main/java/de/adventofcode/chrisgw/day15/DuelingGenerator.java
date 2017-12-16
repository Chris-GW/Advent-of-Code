package de.adventofcode.chrisgw.day15;

import java.util.function.Predicate;


public class DuelingGenerator {

    public static final long DIVIDER = 2147483647;

    private final long factor;
    private final Predicate<Integer> generatedValueCriteria;
    private long previousValue;


    public DuelingGenerator(long factor, long previousValue, Predicate<Integer> generatedValueCriteria) {
        this.factor = factor;
        this.previousValue = previousValue;
        this.generatedValueCriteria = generatedValueCriteria;
    }

    public static DuelingGenerator newDuelingGeneratorA(long startValue, Predicate<Integer> generatedValueCriteria) {
        return new DuelingGenerator(16807, startValue, generatedValueCriteria);
    }

    public static DuelingGenerator newDuelingGeneratorB(long startValue, Predicate<Integer> generatedValueCriteria) {
        return new DuelingGenerator(48271, startValue, generatedValueCriteria);
    }


    public int generateNextValue() {
        int reminder;
        do {
            long product = previousValue * factor;
            reminder = (int) (product % DIVIDER);
            this.previousValue = reminder;
        } while (!generatedValueCriteria.test(reminder));
        return reminder;
    }


    public long getFactor() {
        return factor;
    }

    public long getPreviousValue() {
        return previousValue;
    }

    public Predicate<Integer> getGeneratedValueCriteria() {
        return generatedValueCriteria;
    }

}
