package de.adventofcode.chrisgw.day13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay13Test {

    private static final List<String> inputLinesExample = List.of( //s
            "[1,1,3,1,1]", //
            "[1,1,5,1,1]", //
            "", //
            "[[1],[2,3,4]]", //
            "[[1],4]", //
            "", //
            "[9]", //
            "[[8,7,6]]", //
            "", //
            "[[4,4],4,4]", //
            "[[4,4],4,4,4]", //
            "", //
            "[7,7,7,7]", //
            "[7,7,7]", //
            "", //
            "[]", //
            "[3]", //
            "", //
            "[[[]]]", //
            "[[]]", //
            "", //
            "[1,[2,[3,[4,[5,6,7]]]],8,9]", //
            "[1,[2,[3,[4,[5,6,0]]]],8,9]");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay13(inputLinesExample).solveFirstPart();
        assertEquals(13, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay13.txt");
        int result = new AdventOfCodeDay13(inputLines).solveFirstPart();
        assertEquals(6070, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay13(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay13.txt");
        int result = new AdventOfCodeDay13(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
