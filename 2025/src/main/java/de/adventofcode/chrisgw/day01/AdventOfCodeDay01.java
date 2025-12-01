package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2025/day/1">Advent of Code - day 1</a>
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzleSolver {

    private int dial = 50;


    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2025), 1, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<Rotation> rotations = inputLines().map(Rotation::parse).toList();
        return rotations.stream().mapToInt(this::applyRotationFirstPart).sum();
    }

    private int applyRotationFirstPart(Rotation rotation) {
        int pointedAtZeroCount = 0;
        int sign = rotation.direction().sign();
        int distance = rotation.distance() % 100;
        dial += sign * distance;
        dial %= 100;
        if (dial == 0) {
            pointedAtZeroCount++;
        }
        return pointedAtZeroCount;
    }


    @Override
    public Integer solveSecondPart() {
        List<Rotation> rotations = inputLines().map(Rotation::parse).toList();
        return rotations.stream().mapToInt(this::applyRotationSecondPart).sum();
    }

    private int applyRotationSecondPart(Rotation rotation) {
        int pointedAtZeroCount = 0;
        for (int i = 0; i < rotation.distance(); i++) {
            dial += rotation.direction().sign();
            dial %= 100;
            if (dial == 0) {
                pointedAtZeroCount++;
            }
        }
        return pointedAtZeroCount;
    }

}
