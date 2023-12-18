package de.adventofcode.chrisgw.day18;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay18Test {

    private static final List<String> inputLinesExample = List.of( //
            "R 6 (#70c710)", //
            "D 5 (#0dc571)", //
            "L 2 (#5713f0)", //
            "D 2 (#d2c081)", //
            "R 2 (#59c680)", //
            "D 2 (#411b91)", //
            "L 5 (#8ceee2)", //
            "U 2 (#caa173)", //
            "L 1 (#1b58a2)", //
            "U 2 (#caa171)", //
            "R 2 (#7807d2)", //
            "U 3 (#a77fa3)", //
            "L 2 (#015232)", //
            "U 2 (#7a21e3)");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay18(inputLinesExample).solveFirstPart();
        assertEquals(62, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay18.txt");
        int result = new AdventOfCodeDay18(inputLines).solveFirstPart();
        assertNotEquals(42941, result, "firstPart myPuzzleInput");
        assertNotEquals(41494, result, "firstPart myPuzzleInput"); // high
        assertEquals(41494, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay18(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay18.txt");
        int result = new AdventOfCodeDay18(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
