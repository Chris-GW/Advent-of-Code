package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2023/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2023), 3, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        EngineSchematic engineSchematic = EngineSchematic.parseEngineSchematic(getInputLines());
        return engineSchematic.partNumberSum();
    }


    @Override
    public Integer solveSecondPart() {
        EngineSchematic engineSchematic = EngineSchematic.parseEngineSchematic(getInputLines());
        return engineSchematic.gearRatioSum();
    }

}
