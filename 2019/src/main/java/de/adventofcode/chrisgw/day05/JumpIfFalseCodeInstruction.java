package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.day02.IntCodeInstruction;
import de.adventofcode.chrisgw.day02.IntCodeProgram;


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
        int testValue = intCodeProgram.parameterAt(0);
        if (testValue == 0) {
            int jumpDestination = intCodeProgram.parameterAt(1);
            intCodeProgram.moveInstructionPointerTo(jumpDestination);
        }
    }

}
