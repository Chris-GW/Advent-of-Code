package de.adventofcode.chrisgw.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay10Test {

    private static final List<String> inputLinesExample = List.of(
            "..F7.",
            ".FJ|.",
            "SJ.L7",
            "|F--J",
            "LJ...");

    private static final List<String> inputLinesSimpleExample = List.of(
            ".....",
            ".S-7.",
            ".|.|.",
            ".L-J.",
            ".....");

    @Test
    void solveAocPuzzle_firstPart_simpleExample() {
        int result = new AdventOfCodeDay10(inputLinesSimpleExample).solveFirstPart();
        assertEquals(4, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay10(inputLinesExample).solveFirstPart();
        assertEquals(8, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(6806, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay10(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
