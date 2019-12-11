package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay11Test {

    @Test
    public void myPuzzleInput_part01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay11.txt");
        IntCodeProgram robotProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgrammStr);
        long expectedPaintedPanelsCount = 2415;

        long paintedPanelsCount = AdventOfCodeDay11.paintedPanelsCount(robotProgram);
        assertEquals("paintedPanelsCount", expectedPaintedPanelsCount, paintedPanelsCount);
    }


    @Test
    public void myPuzzleInput_part02() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay11.txt");
        IntCodeProgram robotProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgrammStr);
        String expectedHullIdentificationStr = "" //
                + ".###..####.###..#..#.####.#..#.###...##....\n" //
                + ".#..#.#....#..#.#..#....#.#..#.#..#.#..#...\n" //
                + ".###..###..#..#.#..#...#..#..#.#..#.#......\n" //
                + ".#..#.#....###..#..#..#...#..#.###..#......\n" //
                + ".#..#.#....#....#..#.#....#..#.#....#..#.→.\n" //
                + ".###..#....#.....##..####..##..#.....##....\n";

        String hullIdentificationStr = AdventOfCodeDay11.runPaintingRobotOnWhiteStartingPanel(robotProgram);
        assertEquals("hullIdentificationStr", expectedHullIdentificationStr, hullIdentificationStr);
    }

}
