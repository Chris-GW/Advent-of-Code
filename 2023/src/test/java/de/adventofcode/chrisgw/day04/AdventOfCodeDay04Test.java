package de.adventofcode.chrisgw.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay04Test {

    private static final List<String> inputLinesExample = List.of( //
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53", //
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19", //
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1", //
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83", //
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36", //
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay04(inputLinesExample).solveFirstPart();
        assertEquals(13, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        int result = new AdventOfCodeDay04(inputLines).solveFirstPart();
        assertEquals(18519, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay04(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        int result = new AdventOfCodeDay04(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
