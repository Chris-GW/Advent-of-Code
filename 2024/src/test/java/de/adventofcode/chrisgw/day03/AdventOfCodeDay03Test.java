package de.adventofcode.chrisgw.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay03Test {

    private static final List<String> inputLinesExample = List.of(
            "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveFirstPart();
        int expected = 2 * 4 + 5 * 5 + 11 * 8 + 8 * 5;
        assertEquals(161, expected, "expected result");
        assertEquals(expected, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveFirstPart();
        assertEquals(1, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
