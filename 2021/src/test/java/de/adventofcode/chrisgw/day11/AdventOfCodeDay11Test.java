package de.adventofcode.chrisgw.day11;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;

public class AdventOfCodeDay11Test {

    private static final List<String> inputLinesExample = List.of( //
            "5483143223", //
            "2745854711", //
            "5264556173", //
            "6141336146", //
            "6357385478", //
            "4167524645", //
            "2176841721", //
            "6882881134", //
            "4846848554", //
            "5283751526");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay11(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 1656, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        int result = new AdventOfCodeDay11(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 1691, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay11(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        int result = new AdventOfCodeDay11(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
