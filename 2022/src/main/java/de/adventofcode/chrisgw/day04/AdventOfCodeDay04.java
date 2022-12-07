package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/4">Advent of Code - day 4</a>
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2022), 4, inputLines);
    }


    public Integer solveFirstPart() {
        long count = inputLines().map(CleaningSectionAssignmentPair::parseCleaningSectionAssignmentPair)
                .filter(CleaningSectionAssignmentPair::hasFullyContainedSectionAssignment)
                .count();
        return Math.toIntExact(count);
    }


    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 1;
    }


}
