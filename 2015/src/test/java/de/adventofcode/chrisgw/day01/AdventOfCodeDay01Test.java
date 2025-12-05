package de.adventofcode.chrisgw.day01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay01Test {


    private static Stream<Arguments> provideExamples() {
        return Stream.of(
                Arguments.of("(())", 0),
                Arguments.of("()()", 0),
                Arguments.of("(((", 3),
                Arguments.of("(()(()(", 3),
                Arguments.of("))(((((", 3),
                Arguments.of("())", -1),
                Arguments.of("))(", -1),
                Arguments.of(")))", -3),
                Arguments.of(")())())", -3)
        );
    }


    @ParameterizedTest
    @MethodSource("provideExamples")
    void solveAocPuzzle_firstPart_example(String input, int expected) {
        List<String> inputLines = List.of(input);
        int result = new AdventOfCodeDay01(inputLines).solveFirstPart();
        assertEquals(expected, result, "firstPart example");
    }


    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveFirstPart();
        assertEquals(138, result, "firstPart myPuzzleInput");
    }


    @ParameterizedTest
    @MethodSource("provideExamples")
    void solveAocPuzzle_secondPart_example() {
        List<String> inputLines = List.of("");
        int result = new AdventOfCodeDay01(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt");
        int result = new AdventOfCodeDay01(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
