package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay02Test {


    private final List<String> inputLinesExample = List.of( //
            "forward 5", //
            "down 5", //
            "forward 8", //
            "up 3", //
            "down 8", //
            "forward 2");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay02(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", (15 * 10), result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> commandLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        int result = new AdventOfCodeDay02(commandLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 1499229, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay02(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", (15 * 60), result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> commandLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        int result = new AdventOfCodeDay02(commandLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1340836560, result);
    }

}
