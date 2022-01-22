package de.adventofcode.chrisgw.day12;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.assertEquals;

public class AdventOfCodeDay12Test {

    private static final List<String> inputLinesExample1 = List.of( //
            "start-A", //
            "start-b", //
            "A-c", //
            "A-b", //
            "b-d", //
            "A-end", //
            "b-end");


    @Test
    public void solveAocPuzzle_firstPart_example1() {
        int result = new AdventOfCodeDay12(inputLinesExample1).solveFirstPart();
        assertEquals("firstPart example", 10, result);
    }


    private static final List<String> inputLinesExample2 = List.of( //
            "dc-end", //
            "HN-start", //
            "start-kj", //
            "dc-start", //
            "dc-HN", //
            "LN-dc", //
            "HN-end", //
            "kj-sa", //
            "kj-HN", //
            "kj-dc");

    @Test
    public void solveAocPuzzle_firstPart_example2() {
        int result = new AdventOfCodeDay12(inputLinesExample2).solveFirstPart();
        assertEquals("firstPart example", 19, result);
    }


    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 3679, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay12(inputLinesExample1).solveSecondPart();
        assertEquals("secondPart example", 1, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 1, result);
    }

}
