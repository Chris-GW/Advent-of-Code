package de.adventofcode.chrisgw.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay10Test {


    @Test
    void solveAocPuzzle_firstPart_example1() {
        List<String> inputLinesExample = List.of(
                "0123",
                "1234",
                "8765",
                "9876");
        int result = new AdventOfCodeDay10(inputLinesExample).solveFirstPart();
        assertEquals(1, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example2() {
        List<String> inputLinesExample = List.of(
                "...0...",
                "...1...",
                "...2...",
                "6543456",
                "7.....7",
                "8.....8",
                "9.....9");
        int result = new AdventOfCodeDay10(inputLinesExample).solveFirstPart();
        assertEquals(2, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example3() {
        List<String> inputLinesExample = List.of(
                "89010123",
                "78121874",
                "87430965",
                "96549874",
                "45678903",
                "32019012",
                "01329801",
                "10456732");
        int result = new AdventOfCodeDay10(inputLinesExample).solveFirstPart();
        assertEquals(36, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals(550, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        List<String> inputLinesExample = List.of("");
        int result = new AdventOfCodeDay10(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
