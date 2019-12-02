package de.adventofcode.chrisgw.day02;

public interface IntCodeInstruction {

    int opCode();
    String instructionName();
    int instructionSize();

    void execute(IntCodeProgram intCodeProgram);

}
