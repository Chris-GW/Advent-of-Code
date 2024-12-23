package de.adventofcode.chrisgw.day09;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay09Test {

    private static final List<String> inputLinesExample = List.of("2333133121414131402");

    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay09(inputLinesExample).solveFirstPart();
        assertEquals(1928L, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        long result = new AdventOfCodeDay09(inputLines).solveFirstPart();
        assertEquals(6346871685398L, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay09(inputLinesExample).solveSecondPart();
        assertEquals(2858L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        long result = new AdventOfCodeDay09(inputLines).solveSecondPart();
        assertEquals(6373055193464L, result, "secondPart myPuzzleInput");
    }

}
