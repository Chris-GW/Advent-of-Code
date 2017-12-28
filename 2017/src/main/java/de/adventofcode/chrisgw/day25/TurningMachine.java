package de.adventofcode.chrisgw.day25;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class TurningMachine {

    private TurningMachineState currentState;
    private int cursorPosition;
    private Map<Integer, Boolean> indexToValueTape;


    public TurningMachine(TurningMachineState startState) {
        this.currentState = startState;
        this.cursorPosition = 0;
        this.indexToValueTape = new HashMap<>();
    }


    public void nextStep() {
        boolean currentValue = getCurrentValue();
        setCurrentValue(currentState.writeValue(currentValue));
        cursorPosition += currentState.moveCursor(currentValue);
        currentState = currentState.nextState(currentValue);
    }


    public boolean getCurrentValue() {
        return getTapeValue(cursorPosition);
    }

    private Boolean getTapeValue(int cursorPosition) {
        return indexToValueTape.computeIfAbsent(cursorPosition, i -> false);
    }

    public boolean setCurrentValue(boolean value) {
        return indexToValueTape.put(cursorPosition, value);
    }


    public long calculateChecksum() {
        return indexToValueTape.values().stream().filter(value -> value).count();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Boolean> sortedTapeValues = indexToValueTape.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .map(Entry::getValue)
                .collect(Collectors.toList());

        for (int i = -3; i <= 2; i++) {
            if (i == cursorPosition) {
                sb.append('[');
            } else {
                sb.append(' ');
            }

            if (getTapeValue(i)) {
                sb.append('1');
            } else {
                sb.append('0');
            }

            if (i == cursorPosition) {
                sb.append(']');
            } else {
                sb.append(' ');
            }
        }

        sb.append("(").append(currentState.getStateName()).append(")");
        return sb.toString();
    }

}
