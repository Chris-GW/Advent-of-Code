package de.adventofcode.chrisgw.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay12Test {

    private static final List<String> inputLinesExample = List.of( //
            "Sabqponm", //
            "abcryxxl", //
            "accszExk", //
            "acctuvwj", //
            "abdefghi");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay12(inputLinesExample).solveFirstPart();
        assertEquals(31, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveFirstPart();
        assertEquals(330, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay12(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
