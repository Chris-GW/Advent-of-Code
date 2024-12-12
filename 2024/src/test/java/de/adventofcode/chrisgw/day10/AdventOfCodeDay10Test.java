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
    void solveAocPuzzle_secondPart_example1() {
        List<String> inputLinesExample = List.of(
                ".....0.",
                "..4321.",
                "..5..2.",
                "..6543.",
                "..7..4.",
                "..8765.",
                "..9....");
        int result = new AdventOfCodeDay10(inputLinesExample).solveSecondPart();
        assertEquals(3, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example2() {
        List<String> inputLinesExample = List.of(
                "..90..9",
                "...1.98",
                "...2..7",
                "6543456",
                "765.987",
                "876....",
                "987....");
        int result = new AdventOfCodeDay10(inputLinesExample).solveSecondPart();
        assertEquals(13, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example3() {
        List<String> inputLinesExample = List.of(
                "012345",
                "123456",
                "234567",
                "345678",
                "4.6789",
                "56789.");
        int result = new AdventOfCodeDay10(inputLinesExample).solveSecondPart();
        assertEquals(227, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example4() {
        List<String> inputLinesExample = List.of(
                "89010123",
                "78121874",
                "87430965",
                "96549874",
                "45678903",
                "32019012",
                "01329801",
                "10456732");
        int result = new AdventOfCodeDay10(inputLinesExample).solveSecondPart();
        assertEquals(81, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        int result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals(1255, result, "secondPart myPuzzleInput");
    }

}
