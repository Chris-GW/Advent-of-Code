package de.adventofcode.chrisgw.day03;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay03Test {

    private static final List<String> inputLinesExample = List.of("^>v<");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveFirstPart();
        assertEquals(4, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveFirstPart();
        assertEquals(2592, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveSecondPart();
        assertEquals(3, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveSecondPart();
        assertEquals(2360, result, "secondPart myPuzzleInput");
    }

}
