package de.adventofcode.chrisgw.day20;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay20Test {

    private static final List<String> inputLinesExample = List.of( //
            "1", //
            "2", //
            "-3", //
            "3", //
            "-2", //
            "0", //
            "4");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay20(inputLinesExample).solveFirstPart();
        assertEquals(3, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay20.txt");
        int result = new AdventOfCodeDay20(inputLines).solveFirstPart();
        assertEquals(23321, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay20(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay20.txt");
        int result = new AdventOfCodeDay20(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
