package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.day02.IntCodeInstruction;
import de.adventofcode.chrisgw.day02.IntCodeProgram;

import static de.adventofcode.chrisgw.day02.IntCodeProgram.PARAMETER_IMMEDIATE_MODE;


public class InputCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 3;
    }

    @Override
    public String instructionName() {
        return "INP";
    }

    @Override
    public int instructionSize() {
        return 2;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        int input = intCodeProgram.nextInput();
        int destinationAddress = intCodeProgram.parameterAt(0, PARAMETER_IMMEDIATE_MODE);
        intCodeProgram.setValueAt(destinationAddress, input);
    }

}
