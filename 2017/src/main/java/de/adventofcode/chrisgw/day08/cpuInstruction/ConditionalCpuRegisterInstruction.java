package de.adventofcode.chrisgw.day08.cpuInstruction;

public interface ConditionalCpuRegisterInstruction extends CpuRegisterInstruction {

    boolean testInsturctionCondition();

}
