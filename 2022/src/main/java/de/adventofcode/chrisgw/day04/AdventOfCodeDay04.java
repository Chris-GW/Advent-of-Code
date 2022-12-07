package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.Predicate;


/**
 * <a href="https://adventofcode.com/2022/day/4">Advent of Code - day 4</a>
 */
public class AdventOfCodeDay04 extends AdventOfCodePuzzleSolver<Long> {

    public AdventOfCodeDay04(List<String> inputLines) {
        super(Year.of(2022), 4, inputLines);
    }


    public Long solveFirstPart() {
        return countOverlappingSectionAssignmentPairs(SectionAssignmentPair::hasFullyContainedSectionAssignment);
    }


    public Long solveSecondPart() {
        return countOverlappingSectionAssignmentPairs(SectionAssignmentPair::hasAnyOverlap);
    }

    private long countOverlappingSectionAssignmentPairs(Predicate<SectionAssignmentPair> isOverlappingAssignment) {
        return inputLines().map(SectionAssignmentPair::parseSectionAssignmentPair)
                .filter(isOverlappingAssignment)
                .count();
    }


}
