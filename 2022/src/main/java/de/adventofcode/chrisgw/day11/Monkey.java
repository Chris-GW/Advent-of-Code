package de.adventofcode.chrisgw.day11;

import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

@Data
public class Monkey {

    private final int monkeyNumber;

    private MonkeyItemInspection monkeyItemInspection;
    private long itemInspectionDivisor;
    private Monkey targetMonkeyInCaseTrue;
    private Monkey targetMonkeyInCaseFalse;

    private Deque<BigInteger> itemWorryLevels = new ArrayDeque<>();
    private BigInteger itemInspectCount = BigInteger.ZERO;


    public Monkey(int monkeyNumber) {
        this.monkeyNumber = monkeyNumber;
    }


    public void takeTurn() {
        while (!itemWorryLevels.isEmpty()) {
            BigInteger worryLevel = itemWorryLevels.pollFirst();
            worryLevel = monkeyItemInspection.apply(worryLevel);
            itemInspectCount = itemInspectCount.add(BigInteger.ONE);
            if (worryLevel.remainder(BigInteger.valueOf(itemInspectionDivisor)).equals(BigInteger.ZERO)) {
                targetMonkeyInCaseTrue.catchItemWithWorryLevel(worryLevel);
            } else {
                targetMonkeyInCaseFalse.catchItemWithWorryLevel(worryLevel);
            }
        }
    }

    public void catchItemWithWorryLevel(BigInteger worryLevel) {
        itemWorryLevels.addLast(worryLevel);
    }

    @Override
    public String toString() {
        return getMonkeyNumber() + "";
    }

}
