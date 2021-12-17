package de.adventofcode.chrisgw.day05;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;


public class AdventOfCodeDay05Test {

    private static final List<String> inputLinesExample = List.of( //
            "0,9 -> 5,9", //
            "8,0 -> 0,8", //
            "9,4 -> 3,4", //
            "2,2 -> 2,1", //
            "7,0 -> 7,4", //
            "6,4 -> 2,0", //
            "0,9 -> 2,9", //
            "3,4 -> 1,4", //
            "0,0 -> 8,8", //
            "5,5 -> 8,2");

    @Test
    public void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay05(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 5L, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay05.txt");
        long result = new AdventOfCodeDay05(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 7269L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay05(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1L, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay05.txt");
        long result = new AdventOfCodeDay05(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1L, result);
    }

}
