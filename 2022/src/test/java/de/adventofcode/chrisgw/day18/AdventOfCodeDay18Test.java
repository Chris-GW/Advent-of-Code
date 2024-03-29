package de.adventofcode.chrisgw.day18;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay18Test {

    private static final List<String> inputLinesExample = List.of( //
            "2,2,2", //
            "1,2,2", //
            "3,2,2", //
            "2,1,2", //
            "2,3,2", //
            "2,2,1", //
            "2,2,3", //
            "2,2,4", //
            "2,2,6", //
            "1,2,5", //
            "3,2,5", //
            "2,1,5", //
            "2,3,5");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay18(inputLinesExample).solveFirstPart();
        assertEquals(64, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay18.txt");
        int result = new AdventOfCodeDay18(inputLines).solveFirstPart();
        assertEquals(3390, result, "firstPart myPuzzleInput");
    }

    // part 2

    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay18(inputLinesExample).solveSecondPart();
        assertEquals(58, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay18.txt");
        int result = new AdventOfCodeDay18(inputLines).solveSecondPart();
        assertEquals(2058, result, "secondPart myPuzzleInput");
    }

}
