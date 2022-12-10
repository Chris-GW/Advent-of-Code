package de.adventofcode.chrisgw.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay10Test {


    @Test
    void solveAocPuzzle_firstPart_simpleExample() {
        List<String> inputLines = List.of( //
                "noop", //
                "addx 3", //
                "addx -5");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(-41440, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDayExample10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(13140, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(16020, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDayExample10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
