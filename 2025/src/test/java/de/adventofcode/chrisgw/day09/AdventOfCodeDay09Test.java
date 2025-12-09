package de.adventofcode.chrisgw.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;


class AdventOfCodeDay09Test {

    private static final List<String> inputLinesExample = List.of(
            "7,1",
            "11,1",
            "11,7",
            "9,7",
            "9,5",
            "2,5",
            "2,3",
            "7,3");

    @Test
    void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay09(inputLinesExample).solveFirstPart();
        assertEquals(50L, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        long result = new AdventOfCodeDay09(inputLines).solveFirstPart();
        assertNotEquals(2147458080L, result, "toLow");
        assertEquals(4772103936L, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay09(inputLinesExample).solveSecondPart();
        assertEquals(24L, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay09.txt");
        long result = new AdventOfCodeDay09(inputLines).solveSecondPart();
        System.out.println(result);
        assertNotEquals(1307611448L, "to low");
        assertTrue(result > 1307611448L, "to low");
        assertEquals(1L, result, "secondPart myPuzzleInput");
    }

}
