package de.adventofcode.chrisgw.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay04Test {

    private static final List<String> inputLinesExample = List.of(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@.");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay04(inputLinesExample).solveFirstPart();
        assertEquals(13, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        int result = new AdventOfCodeDay04(inputLines).solveFirstPart();
        assertEquals(1411, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay04(inputLinesExample).solveSecondPart();
        assertEquals(43, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        int result = new AdventOfCodeDay04(inputLines).solveSecondPart();
        assertEquals(8557, result, "secondPart myPuzzleInput");
    }

}
