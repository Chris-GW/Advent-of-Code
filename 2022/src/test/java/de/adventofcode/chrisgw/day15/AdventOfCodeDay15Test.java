package de.adventofcode.chrisgw.day15;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay15Test {

    private static final List<String> inputLinesExample = List.of("");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay15(inputLinesExample).solveFirstPart();
        assertEquals(1, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay15.txt");
        int result = new AdventOfCodeDay15(inputLines).solveFirstPart();
        assertEquals(1, result, "firstPart myPuzzleInput");
    }

    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay15(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay15.txt");
        int result = new AdventOfCodeDay15(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
