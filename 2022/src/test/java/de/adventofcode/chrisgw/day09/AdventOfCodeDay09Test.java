package de.adventofcode.chrisgw.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay09Test {

    private static final List<String> inputLinesExample = List.of( //
            "R 4", //
            "U 4", //
            "L 3", //
            "D 1", //
            "R 4", //
            "D 1", //
            "L 5", //
            "R 2");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay09(inputLinesExample).solveFirstPart();
        assertEquals(13, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        int result = new AdventOfCodeDay09(inputLines).solveFirstPart();
        assertEquals(5930, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example01() {
        int result = new AdventOfCodeDay09(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example02() {
        List<String> inputLinesExample02 = List.of( //
                "R 5", //
                "U 8", //
                "L 8", //
                "D 3", //
                "R 17", //
                "D 10", //
                "L 25", //
                "U 20");
        int result = new AdventOfCodeDay09(inputLinesExample02).solveSecondPart();
        assertEquals(36, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        int result = new AdventOfCodeDay09(inputLines).solveSecondPart();
        assertEquals(2443, result, "secondPart myPuzzleInput");
    }

}
