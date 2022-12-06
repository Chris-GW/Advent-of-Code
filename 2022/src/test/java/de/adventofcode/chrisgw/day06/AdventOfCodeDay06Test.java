package de.adventofcode.chrisgw.day06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AdventOfCodeDay06Test {

    private static final List<String> inputLinesExample = List.of( //
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb", //
            "bvwbjplbgvbhsrlpgdmjqwftvncz", //
            "nppdvjthqldpwncqszvftbrmjlhg", //
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", //
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" //
    );

    @ParameterizedTest
    @MethodSource("provideDataStreamForFirstPart")
    void solveAocPuzzle_firstPart_examples(String dataStream, int expectedStartOfPacketIndex) {
        int result = new AdventOfCodeDay06(List.of(dataStream)).solveFirstPart();
        assertEquals(expectedStartOfPacketIndex, result, "firstPart example");
    }

    private static Stream<Arguments> provideDataStreamForFirstPart() {
        List<Integer> expectedStartOfPacketIndexes = List.of(7, 5, 6, 10, 11);
        return IntStream.range(0, expectedStartOfPacketIndexes.size())
                .mapToObj(i -> Arguments.of(inputLinesExample.get(i), expectedStartOfPacketIndexes.get(i)));
    }


    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int result = new AdventOfCodeDay06(inputLines).solveFirstPart();
        assertEquals(1361, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay06(inputLinesExample).solveSecondPart();
        assertEquals(2, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int result = new AdventOfCodeDay06(inputLines).solveSecondPart();
        assertEquals(2, result, "secondPart myPuzzleInput");
    }

}
