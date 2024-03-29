package de.adventofcode.chrisgw.day04;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;


public class AdventOfCodeDay04Test {

    private static final List<String> inputLinesExample = List.of(
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1", //
            "", //
            "22 13 17 11  0", //
            "8  2 23  4 24", //
            "21  9 14 16  7", //
            "6 10  3 18  5", //
            "1 12 20 15 19", //
            "", //
            "3 15  0  2 22", //
            "9 18 13 17  5", //
            "19  8  7 25 23", //
            "20 11 10 24  4", //
            "14 21 16 12  6", //
            "", //
            "14 21 17 24  4", //
            "10 16 15  9 19", //
            "18  8 23 26 20", //
            "22 11 13  6  5", //
            "2  0 12  3  7");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay04(inputLinesExample).solveFirstPart();
        assertEquals("firstPart example", 188 * 24, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        int result = new AdventOfCodeDay04(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 11774, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay04(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 148 * 13, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay04.txt");
        int result = new AdventOfCodeDay04(inputLines).solveSecondPart();
        // 6734 to high
        assertEquals("secondPart myPuzzleInput", 4495, result);
    }

}
