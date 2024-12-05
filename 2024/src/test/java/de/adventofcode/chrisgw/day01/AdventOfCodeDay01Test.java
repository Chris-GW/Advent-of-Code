package de.adventofcode.chrisgw.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay01Test {

    private static final List<String> inputLinesExample = List.of(
            "3   4",
            "4   3",
            "2   5",
            "1   3",
            "3   9",
            "3   3");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveFirstPart();
        int totalDistance = 2 + 1 + 0 + 1 + 2 + 5;
        assertEquals(11, totalDistance, "totalDistance");
        assertEquals(totalDistance, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveFirstPart();
        assertEquals(2057374, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveSecondPart();
        int similarityScore = 9 + 4 + 0 + 0 + 9 + 9;
        assertEquals(31, similarityScore, "similarityScore");
        assertEquals(similarityScore, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveSecondPart();
        assertEquals(23177084, result, "secondPart myPuzzleInput");
    }

}
