package de.adventofcode.chrisgw.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay03Test {

    private static final List<String> inputLinesExample = List.of( //
            "467..114..", //
            "...*......", //
            "..35..633.", //
            "......#...", //
            "617*......", //
            ".....+.58.", //
            "..592.....", //
            "......755.", //
            "...$.*....", //
            ".664.598..");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveFirstPart();
        assertEquals(4361, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveFirstPart();
        assertEquals(531932, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveSecondPart();
        assertEquals(467835, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveSecondPart();
        assertEquals(73646890, result, "secondPart myPuzzleInput");
    }

}
