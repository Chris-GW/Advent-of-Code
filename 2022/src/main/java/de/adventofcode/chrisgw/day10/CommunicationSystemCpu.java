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

    private final CrtDisplay crtDisplay = new CrtDisplay();


    public CommunicationSystemCpu(List<CpuInstruction> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }


    public CommunicationSystemCpu nextCycle() {
        if (instructionNeededCycles <= 0) {
            var nextInstruction = instructions.get(instructionPointer);
            instructionNeededCycles = nextInstruction.getNeededCycles();
            System.out.printf("Start cycle %3d: begin executing %s%n", cycle, nextInstruction);
        }

        cycle++;
        instructionNeededCycles--;
        crtDisplay.drawPixel(getRegisterValue());

        if (instructionNeededCycles <= 0) {
            var currentInstruction = instructions.get(instructionPointer);
            currentInstruction.runInstructionOnCpu(this);
            incrementInstructionPointer();
            System.out.printf("End of cycle %2d: finish executing %s (Register X is now %d)%n", cycle - 1, currentInstruction, registerValue);
        }
        System.out.printf("Sprite position: %s%n", printSpritPostion());
        System.out.println();
        return this;
    }

    private String printSpritPostion() {
        int pixelWidth = 40;
        StringBuilder sb = new StringBuilder(pixelWidth);
        for (int i = 0; i < pixelWidth; i++) {
            if (Math.abs(i - registerValue) <= 1) {
                sb.append('#');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    private void incrementInstructionPointer() {
        instructionPointer++;
        if (instructionPointer >= instructions.size()) {
            instructionPointer = 0;
        }
    }


    public int getSignalStrength() {
        return cycle * getRegisterValue();
    }


    public String printCrtDisplay() {
        return crtDisplay.toString();
    }

    @Override
    public String toString() {
        return "%3d | %3d\tsignal = %4d".formatted(cycle, registerValue, getSignalStrength());
    }

}
