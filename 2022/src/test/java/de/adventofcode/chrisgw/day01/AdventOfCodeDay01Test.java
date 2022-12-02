package de.adventofcode.chrisgw.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay01Test {

    private static final List<String> inputLinesExample = List.of("1000", //
            "2000", //
            "3000", //
            "", //
            "4000", //
            "", //
            "5000", //
            "6000", //
            "", //
            "7000", //
            "8000", //
            "9000", //
            "", //
            "10000");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveFirstPart();
        int expectedMaxCalories = 7000 + 8000 + 9000;
        assertEquals(expectedMaxCalories, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveFirstPart();
        assertEquals(66186, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveSecondPart();
        int expectedCaloriesTop3 = 24000 + 11000 + 10000;
        assertEquals(expectedCaloriesTop3, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveSecondPart();
        assertEquals(196804, result, "secondPart myPuzzleInput");
    }

}
