package de.adventofcode.chrisgw.day14;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;


public class AdventOfCodeDay14Test {

    private static final List<String> inputLinesExample = List.of( //
            "NNCB", //
            "", //
            "CH -> B", //
            "HH -> N", //
            "CB -> H", //
            "NH -> C", //
            "HB -> C", //
            "HC -> B", //
            "HN -> C", //
            "NN -> C", //
            "BH -> H", //
            "NC -> B", //
            "NB -> B", //
            "BN -> B", //
            "BB -> N", //
            "BC -> B", //
            "CC -> N", //
            "CN -> C");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay14(inputLinesExample).solveFirstPart();
        int expected = 1749 - 161;
        assertEquals("firstPart example", expected, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay14.txt");
        int result = new AdventOfCodeDay14(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 2509, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay14(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay14.txt");
        int result = new AdventOfCodeDay14(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
