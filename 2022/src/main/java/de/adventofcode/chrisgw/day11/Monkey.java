package de.adventofcode.chrisgw.day11;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.LongUnaryOperator;

@Data
public class Monkey {

    private final int monkeyNumber;

    private LongUnaryOperator itemInspectionOperator;
    private long itemInspectionDivisor;
    private Monkey targetMonkeyInCaseTrue;
    private Monkey targetMonkeyInCaseFalse;

    private Deque<Long> itemWorryLevels = new ArrayDeque<>();
    private long itemInspectCount = 0;


    public Monkey(int monkeyNumber) {
        this.monkeyNumber = monkeyNumber;
    }


    public void takeTurn() {
        while (!itemWorryLevels.isEmpty()) {
            long worryLevel = itemWorryLevels.pollFirst();
            worryLevel = itemInspectionOperator.applyAsLong(worryLevel);
            itemInspectCount++;
            if (worryLevel % itemInspectionDivisor == 0) {
                targetMonkeyInCaseTrue.catchItemWithWorryLevel(worryLevel);
            } else {
                targetMonkeyInCaseFalse.catchItemWithWorryLevel(worryLevel);
            }
        }
    }

    public void catchItemWithWorryLevel(long worryLevel) {
        itemWorryLevels.addLast(worryLevel);
    }


    @Override
    public String toString() {
        return getMonkeyNumber() + ": " + itemWorryLevels;
    }

}
