package de.adventofcode.chrisgw.day10;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.Assert.*;


public class AdventOfCodeDay10Test {

    private static final List<String> inputLinesExample = List.of( //
            "[({(<(())[]>[[{[]{<()<>>", //
            "[(()[<>])]({[<{<<[]>>(", //
            "{([(<{}[<>[]}>{[]{[(<()>", //
            "(((({<>}<{<{<>}{[]{[]{}", //
            "[[<[([]))<([[{}[[()]]]", //
            "[{[{({}]{}}([{[{{{}}([]", //
            "{<[[]]>}<{[{[{[]{()[[[]", //
            "[<(<(<(<{}))><([]([]()", //
            "<{([([[(<>()){}]>(<<{{", //
            "<{([{{}}[<[[[<>{}]]]>[]]");


    @Test
    public void solveAocPuzzle_firstPart_example() {
        long result = new AdventOfCodeDay10(inputLinesExample).solveFirstPart();
        long expectedResult = 6 + 57 + 1197 + 25137;
        assertEquals("firstPart example", expectedResult, result);
    }

    @Test
    public void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        long result = new AdventOfCodeDay10(inputLines).solveFirstPart();
        assertEquals("firstPart myPuzzleInput", 193275, result);
    }


    @Test
    public void solveAocPuzzle_secondPart_example() {
        long result = new AdventOfCodeDay10(inputLinesExample).solveSecondPart();
        assertEquals("secondPart example", 288957, result);
    }

    @Test
    public void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        long result = new AdventOfCodeDay10(inputLines).solveSecondPart();
        assertEquals("secondPart myPuzzleInput", 2429644557L, result);
    }

}
