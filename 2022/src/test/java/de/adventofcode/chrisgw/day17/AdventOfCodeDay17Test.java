package de.adventofcode.chrisgw.day17;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay17Test {

    private static final List<String> inputLinesExample = List.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>");

    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay17(inputLinesExample).solveFirstPart();
        assertEquals(3068, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay17.txt");
        long result = new AdventOfCodeDay17(inputLines).solveFirstPart();
        assertEquals(3081, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay17(inputLinesExample).solveSecondPart();
        assertEquals(1514285714288L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay17.txt");
        long result = new AdventOfCodeDay17(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
