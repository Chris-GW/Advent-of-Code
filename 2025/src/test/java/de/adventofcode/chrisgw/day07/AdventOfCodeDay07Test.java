package de.adventofcode.chrisgw.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay07Test {

    private static final List<String> inputLinesExample = List.of(
            ".......S.......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".....^.^.^.....",
            "...............",
            "....^.^...^....",
            "...............",
            "...^.^...^.^...",
            "...............",
            "..^...^.....^..",
            "...............",
            ".^.^.^.^.^...^.",
            "...............");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay07(inputLinesExample).solveFirstPart();
        assertEquals(21, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        int result = new AdventOfCodeDay07(inputLines).solveFirstPart();
        assertEquals(1507, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay07(inputLinesExample).solveSecondPart();
        assertEquals(40, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        int result = new AdventOfCodeDay07(inputLines).solveSecondPart();
        assertNotEquals(3012, result, "to low");
        assertNotEquals(3014, result, "to low");
        assertNotEquals(4072, result, "to low");
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
