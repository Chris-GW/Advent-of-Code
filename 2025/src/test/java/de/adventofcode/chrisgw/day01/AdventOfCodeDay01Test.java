package de.adventofcode.chrisgw.day01;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay01Test {

    private static final List<String> inputLinesExample = List.of(
            "L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveFirstPart();
        assertEquals(3, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveFirstPart();
        assertEquals(1102, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveSecondPart();
        assertEquals(6, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveSecondPart();
        assertEquals(6175, result, "secondPart myPuzzleInput");
    }

}
