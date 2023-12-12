package de.adventofcode.chrisgw.day12;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.*;

class AdventOfCodeDay12Test {

    private static final List<String> inputLinesExample = List.of( //
            "???.### 1,1,3", //
            ".??..??...?##. 1,1,3", //
            "?#?#?#?#?#?#?#? 1,3,1,6", //
            "????.#...#... 4,1,1", //
            "????.######..#####. 1,6,5", //
            "?###???????? 3,2,1");

    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay12(inputLinesExample).solveFirstPart();
        assertEquals(21, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveFirstPart();
        assertEquals(7084, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay12(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int result = new AdventOfCodeDay12(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
