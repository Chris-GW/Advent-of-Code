package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.day02.IntCodeInstruction;
import de.adventofcode.chrisgw.day02.IntCodeProgram;

import static de.adventofcode.chrisgw.day02.IntCodeProgram.PARAMETER_IMMEDIATE_MODE;


public class EqualsCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 8;
    }

    @Override
    public String instructionName() {
        return "EQU";
    }

    @Override
    public int instructionSize() {
        return 4;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        int firstValue = intCodeProgram.parameterAt(0);
        int secondValue = intCodeProgram.parameterAt(1);
        int destinationAddress = intCodeProgram.parameterAt(2, PARAMETER_IMMEDIATE_MODE);
        if (firstValue == secondValue) {
            intCodeProgram.setValueAt(destinationAddress, 1);
        } else {
            intCodeProgram.setValueAt(destinationAddress, 0);
        }
    }

}
