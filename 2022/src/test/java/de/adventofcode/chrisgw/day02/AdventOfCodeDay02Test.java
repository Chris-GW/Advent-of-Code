package de.adventofcode.chrisgw.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay02Test {

    private static final List<String> inputLinesExample = List.of( //
            "A Y", //
            "B X", //
            "C Z");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay02(inputLinesExample).solveFirstPart();
        int expectedScore = 8 + 1 + 6;
        assertEquals(expectedScore, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        int result = new AdventOfCodeDay02(inputLines).solveFirstPart();
        assertEquals(13446, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay02(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        int result = new AdventOfCodeDay02(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
