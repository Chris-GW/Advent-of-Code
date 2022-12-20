package de.adventofcode.chrisgw.day15;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay15Test {

    private static final List<String> inputLinesExample = List.of( //
            "Sensor at x=2, y=18: closest beacon is at x=-2, y=15", //
            "Sensor at x=9, y=16: closest beacon is at x=10, y=16", //
            "Sensor at x=13, y=2: closest beacon is at x=15, y=3", //
            "Sensor at x=12, y=14: closest beacon is at x=10, y=16", //
            "Sensor at x=10, y=20: closest beacon is at x=10, y=16", //
            "Sensor at x=14, y=17: closest beacon is at x=10, y=16", //
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10", //
            "Sensor at x=2, y=0: closest beacon is at x=2, y=10", //
            "Sensor at x=0, y=11: closest beacon is at x=2, y=10", //
            "Sensor at x=20, y=14: closest beacon is at x=25, y=17", //
            "Sensor at x=17, y=20: closest beacon is at x=21, y=22", //
            "Sensor at x=16, y=7: closest beacon is at x=15, y=3", //
            "Sensor at x=14, y=3: closest beacon is at x=15, y=3", //
            "Sensor at x=20, y=1: closest beacon is at x=15, y=3");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int reviewedRow = 10;
        int result = new AdventOfCodeDay15(inputLinesExample).withReviewedRow(reviewedRow).solveFirstPart();
        assertEquals(26, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        int reviewedRow = 2_000_000;
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay15.txt");
        int result = new AdventOfCodeDay15(inputLines).withReviewedRow(reviewedRow).solveFirstPart();
        assertEquals(5_083_287, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay15(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay15.txt");
        int result = new AdventOfCodeDay15(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
