package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.Map;

import static de.adventofcode.chrisgw.day02.CubeSample.CubeColor.*;


/**
 * <a href="https://adventofcode.com/2023/day/2">Advent of Code - day 2</a>
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay02(List<String> inputLines) {
        super(Year.of(2023), 2, inputLines);
    }


    public Integer solveFirstPart() {
        var maxColorCount = new CubeSample(Map.of(RED, 12, GREEN, 13, BLUE, 14));
        return inputLines().map(CubeGameRecord::parseCubeGameRecord)
                .filter(cubeGameRecord -> cubeGameRecord.isPossibleWith(maxColorCount))
                .mapToInt(CubeGameRecord::id)
                .sum();
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
