package de.adventofcode.chrisgw.day25;

public interface TurningMachineState {

    String getStateName();
    boolean writeValue(boolean currentValue);
    int moveCursor(boolean currentValue);
    TurningMachineState nextState(boolean currentValue);

}
