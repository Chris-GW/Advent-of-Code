package de.adventofcode.chrisgw.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay02Test {

    @Test
    void solveAocPuzzle_firstPart_example01() {
        List<String> inputLines1 = List.of("2x3x4");
        int result = new AdventOfCodeDay02(inputLines1).solveFirstPart();
        int expected = 52 + 6;
        assertEquals(expected, result, "firstPart 01 example");
    }

    @Test
    void solveAocPuzzle_firstPart_example02() {
        List<String> inputLines1 = List.of("1x1x10");
        int result = new AdventOfCodeDay02(inputLines1).solveFirstPart();
        int expected = 42 + 1;
        assertEquals(expected, result, "firstPart 02 example");
    }


    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        int result = new AdventOfCodeDay02(inputLines).solveFirstPart();
        assertEquals(1598415, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example01() {
        List<String> inputLines = List.of("2x3x4");
        int result = new AdventOfCodeDay02(inputLines).solveSecondPart();
        assertEquals(34, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example02() {
        List<String> inputLines = List.of("1x1x10");
        int result = new AdventOfCodeDay02(inputLines).solveSecondPart();
        assertEquals(14, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        int result = new AdventOfCodeDay02(inputLines).solveSecondPart();
        assertEquals(3812909, result, "secondPart myPuzzleInput");
    }

}
