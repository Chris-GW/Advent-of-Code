package de.adventofcode.chrisgw.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay08Test {

    private static final List<String> inputLinesExample = List.of(
            "162,817,812",
            "57,618,57",
            "906,360,560",
            "592,479,940",
            "352,342,300",
            "466,668,158",
            "542,29,236",
            "431,825,988",
            "739,650,466",
            "52,470,668",
            "216,146,977",
            "819,987,18",
            "117,168,530",
            "805,96,715",
            "346,949,466",
            "970,615,88",
            "941,993,340",
            "862,61,35",
            "984,92,344",
            "425,690,689");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay08(inputLinesExample).solveFirstPart();
        assertEquals(40, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new AdventOfCodeDay08(inputLines).solveFirstPart();
        assertNotEquals(1000, result, "to low");
        assertEquals(66640, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay08(inputLinesExample).solveSecondPart();
        assertEquals(25272, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new AdventOfCodeDay08(inputLines).solveSecondPart();
        assertEquals(78894156, result, "secondPart myPuzzleInput");
    }

}
