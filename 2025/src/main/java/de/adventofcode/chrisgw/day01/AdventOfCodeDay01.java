package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2025/day/1">Advent of Code - day 1</a>
 */
public class AdventOfCodeDay01 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay01(List<String> inputLines) {
        super(Year.of(2025), 1, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<Rotation> rotations = inputLines().map(Rotation::parse).toList();
        int pointedAtZero = 0;

        int dial = 50;
        for (Rotation rotation : rotations) {
            int sign = rotation.direction().sign();
            int distance = rotation.distance() % 100;
            dial += sign * distance;
            dial %= 100;
            if (dial == 0) {
                pointedAtZero++;
            }
        }
        return pointedAtZero;
    }


    @Override
    public Integer solveSecondPart() {
        List<Rotation> rotations = inputLines().map(Rotation::parse).toList();
        int pointedAtZero = 0;

        int dial = 50;
        for (Rotation rotation : rotations) {
            int sign = rotation.direction().sign();
            int distance = rotation.distance();
            for (int i = 0; i < distance; i++) {
                dial += sign;
                dial %= 100;
                if (dial == 0) {
                    pointedAtZero++;
                }
            }
        }
        return pointedAtZero;
    }

}
