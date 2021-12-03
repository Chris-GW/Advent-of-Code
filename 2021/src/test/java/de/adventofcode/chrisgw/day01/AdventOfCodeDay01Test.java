package de.adventofcode.chrisgw.day01;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AdventOfCodeDay01Test {

    private static final List<String> inputLinesExample = List.of( //
            "199", //
            "200", //
            "208", //
            "210", //
            "200", //
            "207", //
            "240", //
            "269", //
            "260", //
            "263");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveFirstPart();
        assertThat("firstPart example", result, is(7));
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveFirstPart();
        assertThat("firstPart myPuzzleInput", result, is(1292));
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay01(inputLinesExample).solveSecondPart();
        assertThat("secondPart example", result, is(5));
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveSecondPart();
        assertThat("secondPart myPuzzleInput", result, is(1262));
    }

}
