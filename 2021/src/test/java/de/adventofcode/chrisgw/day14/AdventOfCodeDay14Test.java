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
        long result = new AdventOfCodeDay14(inputLinesExample).solveFirstPart();
        long expected = 1749 - 161;
        assertEquals("firstPart example", expected, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay14.txt");
        long result = new AdventOfCodeDay14(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 2509L, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay14(inputLinesExample).solveSecondPart();
        long expected = 2192039569602L - 3849876073L;
        assertEquals("secondPart example", expected, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay14.txt");
        long result = new AdventOfCodeDay14(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 2827627697643L, result);
    }

}
