package de.adventofcode.chrisgw.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TreeHeightMapTest {

    private static final List<String> inputLinesExample = List.of( //
            "30373", //
            "25512", //
            "65332", //
            "33549", //
            "35390");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new TreeHeightMap(inputLinesExample).solveFirstPart();
        assertEquals(21, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new TreeHeightMap(inputLines).solveFirstPart();
        assertEquals(1827, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new TreeHeightMap(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        int result = new TreeHeightMap(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
