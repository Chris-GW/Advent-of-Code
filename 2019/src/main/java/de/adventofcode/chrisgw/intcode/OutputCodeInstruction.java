package de.adventofcode.chrisgw.intcode;


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
        long output = intCodeProgram.parameterAt(0);
        intCodeProgram.addOutput(output);
    }

}
