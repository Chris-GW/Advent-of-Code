package de.adventofcode.chrisgw.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay02Test {

    private static final List<String> inputLinesExample = List.of(""
            + "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,"
            + "1698522-1698528,446443-446449,38593856-38593862,565653-565659,"
            + "824824821-824824827,2121212118-2121212124");


    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay02(inputLinesExample).solveFirstPart();
        assertEquals(1227775554L, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        long result = new AdventOfCodeDay02(inputLines).solveFirstPart();
        assertEquals(54641809925L, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay02(inputLinesExample).solveSecondPart();
        assertEquals(4174379265L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        long result = new AdventOfCodeDay02(inputLines).solveSecondPart();
        assertEquals(73694270688L, result, "secondPart myPuzzleInput");
    }

}
