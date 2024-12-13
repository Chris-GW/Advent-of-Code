package de.adventofcode.chrisgw.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


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
    void solveAocPuzzle_secondPart_example1() {
        List<String> inputLines = List.of(
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(80, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example2() {
        List<String> inputLines = List.of(
                "OOOOO",
                "OXOXO",
                "OOOOO",
                "OXOXO",
                "OOOOO");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(436, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example3() {
        List<String> inputLines = List.of(
                "EEEEE",
                "EXXXX",
                "EEEEE",
                "EXXXX",
                "EEEEE");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(236, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example4() {
        List<String> inputLines = List.of(
                "AAAAAA",
                "AAABBA",
                "AAABBA",
                "ABBAAA",
                "ABBAAA",
                "AAAAAA");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(368, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_example5() {
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
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(1206, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertNotEquals(843348, result, "to low");
        assertNotEquals(1036236, result, "to heigh");
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
