package de.adventofcode.chrisgw.day02;

import org.junit.Test;

import static org.junit.Assert.*;


public class IntCodeProgramTest {

    @Test
    public void example01() {
        int[] initialState = new int[] { 1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50 };
        int[] expectedFinalMemoryState = new int[] { 3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50 };
        int expectedOutput = 3500;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        while (intCodeProgram.hasNext()) {
            intCodeProgram.next();
        }

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getOutput());
    }

    @Test
    public void example02() {
        int[] initialState = new int[] { 1, 0, 0, 0, 99 };
        int[] expectedFinalMemoryState = new int[] { 2, 0, 0, 0, 99 };
        int expectedOutput = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        while (intCodeProgram.hasNext()) {
            intCodeProgram.next();
        }

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getOutput());
    }

    @Test
    public void example03() {
        int[] initialState = new int[] { 2, 3, 0, 3, 99 };
        int[] expectedFinalMemoryState = new int[] { 2, 3, 0, 6, 99 };
        int expectedOutput = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        while (intCodeProgram.hasNext()) {
            intCodeProgram.next();
        }

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getOutput());
    }

    @Test
    public void example04() {
        int[] initialState = new int[] { 2, 4, 4, 5, 99, 0 };
        int[] expectedFinalMemoryState = new int[] { 2, 4, 4, 5, 99, 9801 };
        int expectedOutput = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        while (intCodeProgram.hasNext()) {
            intCodeProgram.next();
        }

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getOutput());
    }

    @Test
    public void example05() {
        int[] initialState = new int[] { 1, 1, 1, 4, 99, 5, 6, 0, 99 };
        int[] expectedFinalMemoryState = new int[] { 30, 1, 1, 4, 2, 5, 6, 0, 99 };
        int expectedOutput = 30;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        while (intCodeProgram.hasNext()) {
            intCodeProgram.next();
        }

        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
        assertEquals("output", expectedOutput, intCodeProgram.getOutput());
    }


}