package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day02.AdventOfCodeDay02;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay03Test {

    private static final List<String> inputLinesExample = List.of( //
            "00100", //
            "11110", //
            "10110", //
            "10111", //
            "10101", //
            "01111", //
            "00111", //
            "11100", //
            "10000", //
            "11001", //
            "00010", //
            "01010");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 198, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> commandLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(commandLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 4103154, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", (15 * 60), result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> commandLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(commandLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1340836560, result);
    }

}
