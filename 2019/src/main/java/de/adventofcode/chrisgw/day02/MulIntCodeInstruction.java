package de.adventofcode.chrisgw.day02;

import static de.adventofcode.chrisgw.day02.IntCodeProgram.PARAMETER_IMMEDIATE_MODE;


public class MulIntCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 2;
    }

    @Override
    public String instructionName() {
        return "MUL";
    }

    @Override
    public int instructionSize() {
        return 4;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        int firstFactor = intCodeProgram.parameterAt(0);
        int secondFactor = intCodeProgram.parameterAt(1);
        int product = firstFactor * secondFactor;
        int destinationAddress = intCodeProgram.parameterAt(2, PARAMETER_IMMEDIATE_MODE);
        intCodeProgram.setValueAt(destinationAddress, product);
    }

}
