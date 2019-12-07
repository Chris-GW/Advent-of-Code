package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day02.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay07Test {

    @Test
    public void example01_part01() {
        int[] initialState = new int[] { 3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0 };
        int expectedHighestThrusterSignal = 43210;

        IntCodeProgram controllerSoftware = new IntCodeProgram(initialState);
        AdventOfCodeDay07 aocDay07 = new AdventOfCodeDay07(controllerSoftware);
        int highestThrusterSignal = aocDay07.findHighestThrusterSignal();
        assertEquals("highestThrusterSignal", expectedHighestThrusterSignal, highestThrusterSignal);
    }


    @Test
    public void example02_part01() {
        int[] initialState = new int[] { 3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, //
                101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0 };
        int expectedHighestThrusterSignal = 54321;

        IntCodeProgram controllerSoftware = new IntCodeProgram(initialState);
        AdventOfCodeDay07 aocDay07 = new AdventOfCodeDay07(controllerSoftware);
        int highestThrusterSignal = aocDay07.findHighestThrusterSignal();
        assertEquals("highestThrusterSignal", expectedHighestThrusterSignal, highestThrusterSignal);
    }

    @Test
    public void example03_part01() {
        int[] initialState = new int[] { 3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33, //
                1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0 };
        int expectedHighestThrusterSignal = 65210;

        IntCodeProgram controllerSoftware = new IntCodeProgram(initialState);
        AdventOfCodeDay07 aocDay07 = new AdventOfCodeDay07(controllerSoftware);
        int highestThrusterSignal = aocDay07.findHighestThrusterSignal();
        assertEquals("highestThrusterSignal", expectedHighestThrusterSignal, highestThrusterSignal);
    }


    @Test
    public void myPuzzleInput_part01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay07.txt");
        int expectedHighestThrusterSignal = 262086;

        IntCodeProgram controllerSoftware = IntCodeProgram.parseIntCodeProgram(intCodeProgrammStr);
        AdventOfCodeDay07 aocDay07 = new AdventOfCodeDay07(controllerSoftware);
        int highestThrusterSignal = aocDay07.findHighestThrusterSignal();
        assertEquals("highestThrusterSignal", expectedHighestThrusterSignal, highestThrusterSignal);
    }

}