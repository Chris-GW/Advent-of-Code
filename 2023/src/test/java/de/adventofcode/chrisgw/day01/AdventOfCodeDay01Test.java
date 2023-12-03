package de.adventofcode.chrisgw.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay01Test {


    @Test
    void solveAocPuzzle_firstPart_example() {
        List<String> inputLinesExample1 = List.of( //
                "1abc2", //
                "pqr3stu8vwx", //
                "a1b2c3d4e5f", //
                "treb7uchet");
        int result = new AdventOfCodeDay01(inputLinesExample1).solveFirstPart();
        int expected = 12 + 38 + 15 + 77;
        assertEquals(142, expected, "firstPart example expected");
        assertEquals(expected, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveFirstPart();
        assertEquals(55130, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        List<String> inputLinesExample1 = List.of( //
                "two1nine", //
                "eightwothree", //
                "abcone2threexyz", //
                "xtwone3four", //
                "4nineeightseven2", //
                "zoneight234", //
                "7pqrstsixteen");
        int result = new AdventOfCodeDay01(inputLinesExample1).solveFirstPart();
        int expected = 29 + 83 + 13 + 24 + 42 + 14 + 76;
        assertEquals(281, expected, "firstPart example expected");
        assertEquals(expected, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveSecondPart();
        assertEquals(54985, result, "secondPart myPuzzleInput");
    }

}
