package de.adventofcode.chrisgw.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay07Test {

    private static final List<String> inputLinesExample = List.of( //
            "$ cd /", //
            "$ ls", //
            "dir a", //
            "14848514 b.txt", //
            "8504156 c.dat", //
            "dir d", //
            "$ cd a", //
            "$ ls", //
            "dir e", //
            "29116 f", //
            "2557 g", //
            "62596 h.lst", //
            "$ cd e", //
            "$ ls", //
            "584 i", //
            "$ cd ..", //
            "$ cd ..", //
            "$ cd d", //
            "$ ls", //
            "4060174 j", //
            "8033020 d.log", //
            "5626152 d.ext", //
            "7214296 k");

    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay07(inputLinesExample).solveFirstPart();
        long expected = 94853 + 584;
        assertEquals(95437L, expected, "expect total sizes");
        assertEquals(expected, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        long result = new AdventOfCodeDay07(inputLines).solveFirstPart();
        assertEquals(1206825L, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay07(inputLinesExample).solveSecondPart();
        assertEquals(1L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        long result = new AdventOfCodeDay07(inputLines).solveSecondPart();
        assertEquals(1L, result, "secondPart myPuzzleInput");
    }

}
