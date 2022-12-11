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

    private final CrtDisplay crtDisplay;


    public CommunicationSystemCpu(List<CpuInstruction> instructions) {
        int pixelWidth = 40;
        int pixelHeight = 6;
        int spriteWidth = 3;
        this.crtDisplay = new CrtDisplay(pixelWidth, pixelHeight, spriteWidth);
        this.instructions = new ArrayList<>(instructions);
    }


    public CommunicationSystemCpu nextCycle() {
        if (instructionNeededCycles <= 0) {
            var nextInstruction = instructions.get(instructionPointer);
            instructionNeededCycles = nextInstruction.getNeededCycles();
            System.out.printf("Start cycle %3d: begin executing %s%n", cycle, nextInstruction);
        }

        int pixelPosition = crtDisplay.currentPixelPosition();
        crtDisplay.drawPixel(getRegisterValue());
        System.out.printf("During cycle %2d: CRT draws pixel in position %d%n", cycle, pixelPosition);
        System.out.printf("Current CRT row: %s%n", crtDisplay.printCurrentCrtRow());
        instructionNeededCycles--;
        cycle++;

        if (instructionNeededCycles <= 0) {
            var currentInstruction = instructions.get(instructionPointer);
            currentInstruction.runInstructionOnCpu(this);
            instructionPointer++;
            System.out.printf("End of cycle %2d: finish executing %s (Register X is now %d)%n", cycle - 1, currentInstruction, registerValue);
            System.out.printf("Sprite position: %s%n", printSpritPostion());
        }
        System.out.println();
        return this;
    }


    public int getSignalStrength() {
        return cycle * registerValue;
    }


    private String printSpritPostion() {
        int pixelWidth = 40;
        StringBuilder sb = new StringBuilder(pixelWidth);
        for (int column = 0; column < pixelWidth; column++) {
            if (isInsideSpriteRange(column)) {
                sb.append('#');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    private boolean isInsideSpriteRange(int column) {
        int positionDifference = Math.abs(column - registerValue);
        return positionDifference <= (crtDisplay.getSpriteWidth() / 2);
    }


    public String printCrtDisplay() {
        return crtDisplay.toString();
    }

    public boolean isCrtDisplayCompleted() {
        return crtDisplay.hasFinishedDrawing();
    }


    @Override
    public String toString() {
        return "%3d | %3d\tsignal = %4d".formatted(cycle, registerValue, getSignalStrength());
    }

}
