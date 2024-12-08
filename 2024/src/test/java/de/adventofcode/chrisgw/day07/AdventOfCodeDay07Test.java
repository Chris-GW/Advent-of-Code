package de.adventofcode.chrisgw.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay07Test {

    private static final List<String> inputLinesExample = List.of(
            "190: 10 19",
            "3267: 81 40 27",
            "83: 17 5",
            "156: 15 6",
            "7290: 6 8 6 15",
            "161011: 16 10 13",
            "192: 17 8 14",
            "21037: 9 7 18 13",
            "292: 11 6 16 20");

    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay07(inputLinesExample).solveFirstPart();
        assertEquals(3749L, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        long result = new AdventOfCodeDay07(inputLines).solveFirstPart();
        assertEquals(4998764814652L, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay07(inputLinesExample).solveSecondPart();
        assertEquals(11387L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        long result = new AdventOfCodeDay07(inputLines).solveSecondPart();
        assertEquals(37598910447546L, result, "secondPart myPuzzleInput");
    }

}
