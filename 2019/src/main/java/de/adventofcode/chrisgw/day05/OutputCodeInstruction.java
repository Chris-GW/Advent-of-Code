package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.day02.IntCodeInstruction;
import de.adventofcode.chrisgw.day02.IntCodeProgram;


public class OutputCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 4;
    }

    @Override
    public String instructionName() {
        return "OUT";
    }

    @Override
    public int instructionSize() {
        return 2;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        int output = intCodeProgram.parameterAt(0);
        intCodeProgram.addOutput(output);
    }

}
