package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2025/day/10">Advent of Code - day 10</a>
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay10(List<String> inputLines) {
        super(Year.of(2025), 10, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<Machine> machines = inputLines().map(Machine::parseMachineLine).toList();
        return machines.stream()
                .mapToInt(Machine::initializeMachine)
                .sum();
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }




}
