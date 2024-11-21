package de.adventofcode.chrisgw.day09;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay09Test {

    private static final List<String> inputLinesExample = List.of(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay09(inputLinesExample).solveFirstPart();
        assertEquals(114, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        int result = new AdventOfCodeDay09(inputLines).solveFirstPart();
        assertEquals(1861775706, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay09(inputLinesExample).solveSecondPart();
        assertEquals(2, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        int result = new AdventOfCodeDay09(inputLines).solveSecondPart();
        assertEquals(1082, result, "secondPart myPuzzleInput");
    }

}
