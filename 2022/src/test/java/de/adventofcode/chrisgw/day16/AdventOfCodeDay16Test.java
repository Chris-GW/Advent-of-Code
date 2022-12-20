package de.adventofcode.chrisgw.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeDay16Test {

    private static final List<String> inputLinesExample = List.of( //
            "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB", //
            "Valve BB has flow rate=13; tunnels lead to valves CC, AA", //
            "Valve CC has flow rate=2; tunnels lead to valves DD, BB", //
            "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE", //
            "Valve EE has flow rate=3; tunnels lead to valves FF, DD", //
            "Valve FF has flow rate=0; tunnels lead to valves EE, GG", //
            "Valve GG has flow rate=0; tunnels lead to valves FF, HH", //
            "Valve HH has flow rate=22; tunnel leads to valve GG", //
            "Valve II has flow rate=0; tunnels lead to valves AA, JJ", //
            "Valve JJ has flow rate=21; tunnel leads to valve II");


    @Test
    void solveAocPuzzle_firstPart_example() {
        int result = new AdventOfCodeDay16(inputLinesExample).solveFirstPart();
        assertEquals(1651, result, "firstPart example");
    }

    @Test
    void solveAocPuzzle_firstPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        int result = new AdventOfCodeDay16(inputLines).solveFirstPart();
        assertEquals(1, result, "firstPart myPuzzleInput");
    }


    @Test
    void solveAocPuzzle_secondPart_example() {
        int result = new AdventOfCodeDay16(inputLinesExample).solveSecondPart();
        assertEquals(1, result, "secondPart example");
    }

    @Test
    void solveAocPuzzle_secondPart_myPuzzleInput() {
        List<String> inputLines = readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        int result = new AdventOfCodeDay16(inputLines).solveSecondPart();
        assertEquals(1, result, "secondPart myPuzzleInput");
    }

}
