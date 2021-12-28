package de.adventofcode.chrisgw.day09;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.assertEquals;

public class AdventOfCodeDay09Test {

    private static final List<String> inputLinesExample = List.of(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678");

    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay09(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 15, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        int result = new AdventOfCodeDay09(inputLines).solveFirstPart();
        // 1486 to high
        assertEquals("firstPart myPuzzleInput", 486, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay09(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        int result = new AdventOfCodeDay09(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
