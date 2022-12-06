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

    @ParameterizedTest(name = "[{index}] expect startOfPacketMarker at {1} for: {0}")
    @MethodSource("provideDataStreamForFirstPart")
    void solveAocPuzzle_firstPart_examples(String dataStream, int expectedStartOfPacketMarker) {
        int startOfPacketMarker = new AdventOfCodeDay06(List.of(dataStream)).solveFirstPart();
        assertEquals(expectedStartOfPacketMarker, startOfPacketMarker, "firstPart example");
    }

    private static Stream<Arguments> provideDataStreamForFirstPart() {
        List<Integer> expectedStartOfPacketIndexes = List.of(7, 5, 6, 10, 11);
        return IntStream.range(0, expectedStartOfPacketIndexes.size())
                .mapToObj(i -> Arguments.of(inputLinesExample.get(i), expectedStartOfPacketIndexes.get(i)));
    }


    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int startOfPacketMarker = new AdventOfCodeDay06(inputLines).solveFirstPart();
        assertEquals(1361, startOfPacketMarker, "firstPart myPuzzleInput");
    }


    @ParameterizedTest(name = "[{index}] expect startOfMessageMarker at {1} for: {0}")
    @MethodSource("provideDataStreamForSecondPart")
    void solveAocPuzzle_secondPart_example(String dataStream, int expectedStartOfMessageMarker) {
        int startOfMessageMarker = new AdventOfCodeDay06(List.of(dataStream)).solveSecondPart();
        assertEquals(expectedStartOfMessageMarker, startOfMessageMarker, "secondPart example");
    }

    private static Stream<Arguments> provideDataStreamForSecondPart() {
        List<Integer> expectedStartOfPacketIndexes = List.of(19, 23, 23, 29, 26);
        return IntStream.range(0, expectedStartOfPacketIndexes.size())
                .mapToObj(i -> Arguments.of(inputLinesExample.get(i), expectedStartOfPacketIndexes.get(i)));
    }


    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay06.txt");
        int startOfMessageMarker = new AdventOfCodeDay06(inputLines).solveSecondPart();
        assertEquals(3263, startOfMessageMarker, "secondPart myPuzzleInput");
    }

}
