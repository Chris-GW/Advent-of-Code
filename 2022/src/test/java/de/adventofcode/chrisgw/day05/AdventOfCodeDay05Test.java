package de.adventofcode.chrisgw.day05;

import org.junit.jupiter.api.Test;
import java.util.List;
import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay05Test {

    private static final List<String> inputLinesExample = List.of( //
            "    [D]    ", //
            "[N] [C]    ", //
            "[Z] [M] [P]", //
            " 1   2   3 ", //
            "", //
            "move 1 from 2 to 1", //
            "move 3 from 1 to 3", //
            "move 2 from 2 to 1", //
            "move 1 from 1 to 2"); //

    @Test
    void solveAocPuzzle_firstPart_example() {
        String result = new AdventOfCodeDay05(inputLinesExample).solveFirstPart();
        assertEquals("CMZ", result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay05.txt");
        String result = new AdventOfCodeDay05(inputLines).solveFirstPart();
        assertEquals("SHQWSRBDL", result, "firstPart myPuzzleInput");
    }

    @Test
    void solveAocPuzzle_secondPart_example() {
        String result = new AdventOfCodeDay05(inputLinesExample).solveSecondPart();
        assertEquals("MCD", result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay05.txt");
        String result = new AdventOfCodeDay05(inputLines).solveSecondPart();
        assertEquals("CDTQZHBRS", result, "secondPart myPuzzleInput");
    }

}
