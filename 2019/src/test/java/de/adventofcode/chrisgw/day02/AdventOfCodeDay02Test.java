package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay02Test {

    @Test
    public void example01() {
        int[] initialState = new int[] { 1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50 };
        int[] expectedFinalMemoryState = new int[] { 3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50 };
        int expectedOutput = 3500;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getExitOutput());
    }

    @Test
    public void example02() {
        int[] initialState = new int[] { 1, 0, 0, 0, 99 };
        int[] expectedFinalMemoryState = new int[] { 2, 0, 0, 0, 99 };
        int expectedOutput = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getExitOutput());
    }

    @Test
    public void example03() {
        int[] initialState = new int[] { 2, 3, 0, 3, 99 };
        int[] expectedFinalMemoryState = new int[] { 2, 3, 0, 6, 99 };
        int expectedOutput = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getExitOutput());
    }

    @Test
    public void example04() {
        int[] initialState = new int[] { 2, 4, 4, 5, 99, 0 };
        int[] expectedFinalMemoryState = new int[] { 2, 4, 4, 5, 99, 9801 };
        int expectedOutput = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getExitOutput());
    }

    @Test
    public void example05() {
        int[] initialState = new int[] { 1, 1, 1, 4, 99, 5, 6, 0, 99 };
        int[] expectedFinalMemoryState = new int[] { 30, 1, 1, 4, 2, 5, 6, 0, 99 };
        int expectedOutput = 30;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getExitOutput());
    }


    @Test
    public void myPuzzleInputPart01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay02.txt");
        int expectedOutputValue = 7594646;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        int outputValue = adventOfCodeDay02.restoreGravityAssistProgram();
        assertEquals("restoreGravityAssistProgram", expectedOutputValue, outputValue);
    }

    @Test
    public void myPuzzleInputPart02() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay02.txt");
        int whisedOutput = 19690720;
        int expectedResult = 3376;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        int result = adventOfCodeDay02.completeGravityAssistProgram(whisedOutput);
        assertEquals("restoreGravityAssistProgram", expectedResult, result);
    }

}
