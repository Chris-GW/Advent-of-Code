package de.adventofcode.chrisgw.day10;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CommunicationSystemCpu {

    @Getter
    private int cycle = 1;
    private int instructionNeededCycles;

    @Getter
    private final List<CpuInstruction> instructions;
    private int instructionPointer = 0;

    @Getter
    @Setter
    private int registerValue = 1;


    public CommunicationSystemCpu(List<CpuInstruction> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }


    public CommunicationSystemCpu nextCycle() {
        if (instructionNeededCycles <= 0) {
            var nextInstruction = instructions.get(instructionPointer);
            instructionNeededCycles = nextInstruction.getNeededCycles();
        }

        cycle++;
        instructionNeededCycles--;

        if (instructionNeededCycles <= 0) {
            var currentInstruction = instructions.get(instructionPointer++);
            if (instructionPointer >= instructions.size()) {
                instructionPointer = 0;
            }
            currentInstruction.runInstructionOnCpu(this);
        }
        return this;
    }


    public int getSignalStrength() {
        return cycle * getRegisterValue();
    }


    @Override
    public String toString() {
        return "%3d | %3d\tsignal = %4d".formatted(cycle, registerValue, getSignalStrength());
    }

}
