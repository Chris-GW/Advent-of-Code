package de.adventofcode.chrisgw.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay03Test {

    private static final List<String> inputLinesExample = List.of( //
            "vJrwpWtwJgWrhcsFMMfFFhFp", //
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", //
            "PmmdzqPrVvPwwTWBwg", //
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", //
            "ttgJtRGJQctTZtZT", //
            "CrZsJsPPZsGzwwsLwLmpwMDw");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveFirstPart();
        int expected = 16 + 38 + 42 + 22 + 20 + 19;
        assertEquals(157, expected, "expect result for firstPart example");
        assertEquals(expected, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveFirstPart();
        assertEquals(7848, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveSecondPart();
        assertEquals(-1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveSecondPart();
        assertEquals(-1, result, "secondPart myPuzzleInput");
    }

}
