package de.adventofcode.chrisgw.day03;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay03Test {

    private static final List<String> inputLinesExample = List.of(
            "987654321111111",
            "811111111111119",
            "234234234234278",
            "818181911112111");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay03(inputLinesExample).solveFirstPart();
        assertEquals(357, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        int result = new AdventOfCodeDay03(inputLines).solveFirstPart();
        assertEquals(17316, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        BigInteger result = new AdventOfCodeDay03(inputLinesExample).solveSecondPart();
        BigInteger expected = new BigInteger("3121910778619");
        assertEquals(expected, result, "secondPart example");
    }

    @Test
    @Disabled("slow 28s test")
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        BigInteger result = new AdventOfCodeDay03(inputLines).solveSecondPart();
        BigInteger expected = new BigInteger("171741365473332");
        assertEquals(expected, result, "secondPart myPuzzleInput");
    }

}
