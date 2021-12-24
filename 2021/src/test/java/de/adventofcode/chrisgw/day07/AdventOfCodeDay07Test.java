package de.adventofcode.chrisgw.day07;

import org.junit.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;

public class AdventOfCodeDay07Test {

    private static final List<String> inputLinesExample = List.of("16,1,2,0,4,2,7,1,2,14");

    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay07(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 37, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        int result = new AdventOfCodeDay07(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 336131, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay07(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        int result = new AdventOfCodeDay07(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
