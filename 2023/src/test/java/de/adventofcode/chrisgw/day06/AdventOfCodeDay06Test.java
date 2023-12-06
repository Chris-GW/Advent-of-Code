package de.adventofcode.chrisgw.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay06Test {

    private static final List<String> inputLinesExample = List.of( //
            "Time:      7  15   30", //
            "Distance:  9  40  200");

    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay06(inputLinesExample).solveFirstPart();
        long expected = 4 * 8 * 9;
        assertEquals(288L, expected, "expected result");
        assertEquals(expected, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        long result = new AdventOfCodeDay06(inputLines).solveFirstPart();
        assertEquals(3317888L, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay06(inputLinesExample).solveSecondPart();
        assertEquals(71503L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        long result = new AdventOfCodeDay06(inputLines).solveSecondPart();
        assertEquals(24655068L, result, "secondPart myPuzzleInput");
    }

}
