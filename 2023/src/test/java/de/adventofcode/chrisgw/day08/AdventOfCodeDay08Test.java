package de.adventofcode.chrisgw.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay08Test {

    @Test
    void solveAocPuzzle_firstPart_example() {
        List<String> exampleInputLines = List.of( //
                "RL", //
                "", //
                "AAA = (BBB, CCC)", //
                "BBB = (DDD, EEE)", //
                "CCC = (ZZZ, GGG)", //
                "DDD = (DDD, DDD)", //
                "EEE = (EEE, EEE)", //
                "GGG = (GGG, GGG)", //
                "ZZZ = (ZZZ, ZZZ)");
        int result = new AdventOfCodeDay08(exampleInputLines).solveFirstPart();
        assertEquals(2, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_example2() {
        List<String> inputLinesExample2 = List.of( //
                "LLR", //
                "", //
                "AAA = (BBB, BBB)", //
                "BBB = (AAA, ZZZ)", //
                "ZZZ = (ZZZ, ZZZ)");
        int result = new AdventOfCodeDay08(inputLinesExample2).solveFirstPart();
        assertEquals(6, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new AdventOfCodeDay08(inputLines).solveFirstPart();
        assertEquals(21251, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        List<String> exampleInputLines = List.of( //
                "LR", //
                "", //
                "11A = (11B, XXX)", //
                "11B = (XXX, 11Z)", //
                "11Z = (11B, XXX)", //
                "22A = (22B, XXX)", //
                "22B = (22C, 22C)", //
                "22C = (22Z, 22Z)", //
                "22Z = (22B, 22B)", //
                "XXX = (XXX, XXX)");
        int result = new AdventOfCodeDay08(exampleInputLines).solveSecondPart();
        assertEquals(6, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new AdventOfCodeDay08(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
