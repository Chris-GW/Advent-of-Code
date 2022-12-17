package de.adventofcode.chrisgw.day14;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay14Test {

    private static final List<String> inputLinesExample = List.of( //
            "498,4 -> 498,6 -> 496,6", //
            "503,4 -> 502,4 -> 502,9 -> 494,9");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay14(inputLinesExample).solveFirstPart();
        assertEquals(24, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay14.txt");
        int result = new AdventOfCodeDay14(inputLines).solveFirstPart();
        assertEquals(885, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay14(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay14.txt");
        int result = new AdventOfCodeDay14(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
