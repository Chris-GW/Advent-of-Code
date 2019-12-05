package de.adventofcode.chrisgw.day02;

import static de.adventofcode.chrisgw.day02.IntCodeProgram.PARAMETER_IMMEDIATE_MODE;


public class AddIntCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 1;
    }

    @Override
    public String instructionName() {
        return "ADD";
    }

    @Override
    public int instructionSize() {
        return 4;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        int firstSummand = intCodeProgram.parameterAt(0);
        int secondSummand = intCodeProgram.parameterAt(1);
        int sum = firstSummand + secondSummand;
        int destinationAddress = intCodeProgram.parameterAt(2, PARAMETER_IMMEDIATE_MODE);
        intCodeProgram.setValueAt(destinationAddress, sum);
    }

}
