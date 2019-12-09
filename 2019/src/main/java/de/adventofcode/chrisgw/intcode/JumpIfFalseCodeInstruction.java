package de.adventofcode.chrisgw.intcode;


public class JumpIfFalseCodeInstruction implements IntCodeInstruction {

    @Override
    public int opCode() {
        return 6;
    }

    @Override
    public String instructionName() {
        return "JPF";
    }

    @Override
    public int instructionSize() {
        return 3;
    }

    @Override
    public void execute(IntCodeProgram intCodeProgram) {
        long testValue = intCodeProgram.parameterAt(0);
        if (testValue == 0) {
            int jumpDestination = (int) intCodeProgram.parameterAt(1);
            intCodeProgram.moveInstructionPointerTo(jumpDestination);
        }
    }

}
