package de.adventofcode.chrisgw.intcode;


public class AdjustRelativeBaseCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 9;
    }

    @Override
    public String instructionName() {
        return "SRB";
    }

    @Override
    public int instructionSize() {
        return 2;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        long parameterValue = intCodeProgram.parameterAt(0);
        intCodeProgram.adjustRelativeBase(parameterValue);
    }

}
