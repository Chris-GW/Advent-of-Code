package de.adventofcode.chrisgw.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay04Test {

    private static final List<String> inputLinesExample = List.of( //
            "2-4,6-8", //
            "2-3,4-5", //
            "5-7,7-9", //
            "2-8,3-7", //
            "6-6,4-6", //
            "2-6,4-8");


    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay04(inputLinesExample).solveFirstPart();
        assertEquals(2L, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        long result = new AdventOfCodeDay04(inputLines).solveFirstPart();
        assertEquals(518L, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay04(inputLinesExample).solveSecondPart();
        assertEquals(4L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        long result = new AdventOfCodeDay04(inputLines).solveSecondPart();
        assertEquals(909L, result, "secondPart myPuzzleInput");
    }

}
