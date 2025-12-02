package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2025/day/1">Advent of Code - day 1</a>
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzleSolver {

    private int dial = 50;
    private int endedAtZeroCount = 0;
    private int pointedAtZeroCount = 0;


    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2025), 1, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        inputLines().map(Rotation::parse).forEachOrdered(this::applyRotation);
        return endedAtZeroCount;
    }

    @Override
    public Integer solveSecondPart() {
        inputLines().map(Rotation::parse).forEachOrdered(this::applyRotation);
        return pointedAtZeroCount;
    }


    private void applyRotation(Rotation rotation) {
        for (int i = 0; i < rotation.distance(); i++) {
            dial += rotation.direction().sign();
            dial %= 100;
            if (dial == 0) {
                pointedAtZeroCount++;
            }
        }
        if (dial == 0) {
            endedAtZeroCount++;
        }
    }

}
