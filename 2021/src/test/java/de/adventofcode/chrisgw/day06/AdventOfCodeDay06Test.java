package de.adventofcode.chrisgw.day06;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;


public class AdventOfCodeDay06Test {

    private static final List<String> inputLinesExample = List.of("3,4,3,1,2");

    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay06(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 5934, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int result = new AdventOfCodeDay06(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 354564, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay06(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int result = new AdventOfCodeDay06(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
