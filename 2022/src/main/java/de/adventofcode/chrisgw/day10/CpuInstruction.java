package de.adventofcode.chrisgw.day10;

import lombok.Getter;

public abstract class CpuInstruction {


    @Getter
    private final String instructionName;
    @Getter
    private final int neededCycles;


    CpuInstruction(String instructionName, int neededCycles) {
        this.instructionName = instructionName;
        this.neededCycles = neededCycles;
    }

    public static CpuInstruction parseCpuInstruction(String instructionString) {
        if (instructionString.startsWith("noop")) {
            return new NoopCpuInstruction();
        } else if (instructionString.startsWith("addx")) {
            int value = Integer.parseInt(instructionString.substring("addx ".length()));
            return new AddxCpuInstruction(value);
        } else {
            throw new IllegalArgumentException("unknown cpuInstruction: " + instructionString);
        }
    }


    public abstract void runInstructionOnCpu(CommunicationSystemCpu cpu);


}
