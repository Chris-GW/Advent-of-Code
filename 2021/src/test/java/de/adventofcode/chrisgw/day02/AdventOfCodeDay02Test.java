package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay02Test {

    private final List<String> commandLinesExample = List.of( //
            "forward 5", //
            "down 5", //
            "forward 8", //
            "up 3", //
            "down 8", //
            "forward 2");


    @Test
    public void part01_example() {
        int result = AdventOfCodeDay02.part1(commandLinesExample);
        assertEquals("part01", (15 * 10), result);
    }

    @Test
    public void part01_myPuzzleInput() {
        List<String> commandLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        int result = AdventOfCodeDay02.part1(commandLines);
        assertEquals("part01", 1499229, result);
    }

}
