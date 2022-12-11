package de.adventofcode.chrisgw.day11;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.IntUnaryOperator;

@Data
public class Monkey {

    private final int monkeyNumber;

    private IntUnaryOperator inspectStolenItemOperator;
    private int itemInspectionDivisor;
    private Monkey targetMonkeyInCaseTrue;
    private Monkey targetMonkeyInCaseFalse;

    private Deque<Integer> itemWorryLevels = new ArrayDeque<>();
    private int itemInspectCount = 0;


    public Monkey(int monkeyNumber) {
        this.monkeyNumber = monkeyNumber;
    }


    public void takeTurn() {
        while (!itemWorryLevels.isEmpty()) {
            int worryLevel = itemWorryLevels.pollFirst();
            worryLevel = inspectStolenItemOperator.applyAsInt(worryLevel);
            worryLevel = worryLevel / 3;
            itemInspectCount++;
            if (worryLevel % itemInspectionDivisor == 0) {
                targetMonkeyInCaseTrue.catchItemWithWorryLevel(worryLevel);
            } else {
                targetMonkeyInCaseFalse.catchItemWithWorryLevel(worryLevel);
            }
        }
    }

    public void catchItemWithWorryLevel(int worryLevel) {
        itemWorryLevels.addLast(worryLevel);
    }

    @Override
    public String toString() {
        return getMonkeyNumber() + "";
    }

}
