package de.adventofcode.chrisgw.day15;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;


public class AdventOfCodeDay15Test {

    private static final List<String> inputLinesExample = List.of( //
            "1163751742", //
            "1381373672", //
            "2136511328", //
            "3694931569", //
            "7463417111", //
            "1319128137", //
            "1359912421", //
            "3125421639", //
            "1293138521", //
            "2311944581");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay15(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay15.txt");
        int result = new AdventOfCodeDay15(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 1, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay15(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay15.txt");
        int result = new AdventOfCodeDay15(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
