package de.adventofcode.chrisgw.intcode;


public class JumpIfTrueCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 5;
    }

    @Override
    public String instructionName() {
        return "JPT";
    }

    @Override
    public int instructionSize() {
        return 3;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        long testValue = intCodeProgram.parameterAt(0);
        if (testValue != 0) {
            int jumpDestination = (int) intCodeProgram.parameterAt(1);
            intCodeProgram.moveInstructionPointerTo(jumpDestination);
        }
    }

}
