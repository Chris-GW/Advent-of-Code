package de.adventofcode.chrisgw.day06;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay06Test {

    private static final List<String> inputLinesExample = List.of(
            "....#.....",
                    ".........#",
                    "..........",
                    "..#.......",
                    ".......#..",
                    "..........",
                    ".#..^.....",
                    "........#.",
                    "#.........",
                    "......#...");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay06(inputLinesExample).solveFirstPart();
        assertEquals(41, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int result = new AdventOfCodeDay06(inputLines).solveFirstPart();
        assertEquals(4776, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay06(inputLinesExample).solveSecondPart();
        assertEquals(6, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int result = new AdventOfCodeDay06(inputLines).solveSecondPart();
        assertEquals(1586, result, "secondPart myPuzzleInput");
    }

}
