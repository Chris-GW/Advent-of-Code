package de.adventofcode.chrisgw.day08;

import org.junit.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;

public class AdventOfCodeDay08Test {

    private static final List<String> inputLinesExample = List.of("");

    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay08(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new AdventOfCodeDay08(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay08(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new AdventOfCodeDay08(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
