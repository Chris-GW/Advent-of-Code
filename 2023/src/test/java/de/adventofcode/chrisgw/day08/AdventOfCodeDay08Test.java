package de.adventofcode.chrisgw.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay08Test {

    private static final List<String> inputLinesExample = List.of( //
            "RL", //
            "", //
            "AAA = (BBB, CCC)", //
            "BBB = (DDD, EEE)", //
            "CCC = (ZZZ, GGG)", //
            "DDD = (DDD, DDD)", //
            "EEE = (EEE, EEE)", //
            "GGG = (GGG, GGG)", //
            "ZZZ = (ZZZ, ZZZ)");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay08(inputLinesExample).solveFirstPart();
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
        int result = new AdventOfCodeDay08(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new AdventOfCodeDay08(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
