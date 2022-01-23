package de.adventofcode.chrisgw.day13;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.assertEquals;

public class AdventOfCodeDay13Test {

    private static final List<String> inputLinesExample = List.of( //
            "6,10", //
            "0,14", //
            "9,10", //
            "0,3", //
            "10,4", //
            "4,11", //
            "6,0", //
            "6,12", //
            "4,1", //
            "0,13", //
            "10,12", //
            "3,4", //
            "3,0", //
            "8,4", //
            "1,10", //
            "2,14", //
            "8,10", //
            "9,0", //
            "", //
            "fold along y=7", //
            "fold along x=5");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay13(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 17, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay13.txt");
        int result = new AdventOfCodeDay13(inputLines).solveFirstPart();
        // 1020 wrong
        assertEquals("firstPart myPuzzleInput", 850, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay13(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay13.txt");
        int result = new AdventOfCodeDay13(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
