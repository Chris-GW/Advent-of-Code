package de.adventofcode.chrisgw.intcode;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;


public interface IntCodeInstruction {

    int opCode();
    String instructionName();
    int instructionSize();

    void execute(IntCodeProgram intCodeProgram);

}
