package de.adventofcode.chrisgw.day19;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay19Test {

    private static final List<String> inputLinesExample = List.of( //
            "Blueprint 1:" //
                    + "  Each ore robot costs 4 ore." //
                    + "  Each clay robot costs 2 ore." //
                    + "  Each obsidian robot costs 3 ore and 14 clay." //
                    + "  Each geode robot costs 2 ore and 7 obsidian.", //
            "Blueprint 2:" //
                    + "  Each ore robot costs 2 ore." //
                    + "  Each clay robot costs 3 ore." //
                    + "  Each obsidian robot costs 3 ore and 8 clay." //
                    + "  Each geode robot costs 3 ore and 12 obsidian."); //


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay19(inputLinesExample).solveFirstPart();
        assertEquals(33, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay19.txt");
        int result = new AdventOfCodeDay19(inputLines).solveFirstPart();
        assertEquals(1, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay19(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay19.txt");
        int result = new AdventOfCodeDay19(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
