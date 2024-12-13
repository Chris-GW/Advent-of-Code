package de.adventofcode.chrisgw.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay12Test {

    @Test
    void solveAocPuzzle_firstPart_example1() {
        List<String> inputLines = List.of(
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC");
        int result = new AdventOfCodeDay12(inputLines).solveFirstPart();
        assertEquals(140, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example2() {
        List<String> inputLines = List.of(
                "OOOOO",
                "OXOXO",
                "OOOOO",
                "OXOXO",
                "OOOOO");
        int result = new AdventOfCodeDay12(inputLines).solveFirstPart();
        assertEquals(772, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example3() {
        List<String> inputLines = List.of(
                "RRRRIICCFF",
                "RRRRIICCCF",
                "VVRRRCCFFF",
                "VVRCCCJFFF",
                "VVVVCJJCFE",
                "VVIVCCJJEE",
                "VVIIICJJEE",
                "MIIIIIJJEE",
                "MIIISIJEEE",
                "MMMISSJEEE");
        int result = new AdventOfCodeDay12(inputLines).solveFirstPart();
        assertEquals(1930, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveFirstPart();
        assertEquals(1433460, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay12(List.of("")).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
