package de.adventofcode.chrisgw.day11;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.UnaryOperator;

@Data
public class Monkey {

    private final int monkeyNumber;

    private UnaryOperator<StolenItem> inspectStolenItemOperator;
    private int itemInspectionDivisor;
    private Monkey targetMonkeyInCaseTrue;
    private Monkey targetMonkeyInCaseFalse;

    private Deque<StolenItem> stolenItems = new ArrayDeque<>();
    private int itemInspectCount = 0;


    public Monkey(int monkeyNumber) {
        this.monkeyNumber = monkeyNumber;
    }


    public void takeTurn() {
        while (!stolenItems.isEmpty()) {
            StolenItem item = stolenItems.pollFirst();
            StolenItem inspectedItem = inspectStolenItemOperator.apply(item);
            itemInspectCount++;
            StolenItem boardItemLevel = new StolenItem(inspectedItem.worryLevel() / 3);
            if (boardItemLevel.worryLevel() % itemInspectionDivisor == 0) {
                targetMonkeyInCaseTrue.catchItem(boardItemLevel);
            } else {
                targetMonkeyInCaseFalse.catchItem(boardItemLevel);
            }
        }
    }

    public void catchItem(StolenItem item) {
        stolenItems.addLast(item);
    }

    @Override
    public String toString() {
        return getMonkeyNumber() + "";
    }

}
