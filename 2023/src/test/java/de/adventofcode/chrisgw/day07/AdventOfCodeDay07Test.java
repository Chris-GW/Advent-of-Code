package de.adventofcode.chrisgw.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay07Test {

    private static final List<String> inputLinesExample = List.of( //
            "32T3K 765", //
            "T55J5 684", //
            "KK677 28", //
            "KTJJT 220", //
            "QQQJA 483");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay07(inputLinesExample).solveFirstPart();
        assertEquals(6440, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        int result = new AdventOfCodeDay07(inputLines).solveFirstPart();
        assertEquals(250453939, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay07(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        int result = new AdventOfCodeDay07(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
