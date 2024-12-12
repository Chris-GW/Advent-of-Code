package de.adventofcode.chrisgw.day11;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay11Test {

    private static final List<String> inputLinesExample = List.of("125 17");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay11(inputLinesExample).solveFirstPart();
        assertEquals(55312, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        int result = new AdventOfCodeDay11(inputLines).solveFirstPart();
        assertEquals(194557, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay11(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        int result = new AdventOfCodeDay11(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
