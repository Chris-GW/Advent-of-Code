package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class AdventOfCodeDay09Test {

    @Test
    public void example01_part01_copyOfItself() {
        long[] initialState = new long[] { 109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99 };

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();
        long[] outputs = intCodeProgram.getAllOutput().toArray();
        assertArrayEquals("outputs", initialState, outputs);
    }

    @Test
    public void example02_part01_output16DigitNumber() {
        long[] initialState = new long[] { 1102, 34915192, 34915192, 7, 4, 7, 99, 0 };
        long[] expectedOutputs = new long[] { 1219070632396864L };

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();
        long[] outputs = intCodeProgram.getAllOutput().toArray();
        System.out.println(Arrays.toString(outputs));
        assertArrayEquals("outputs", expectedOutputs, outputs);
    }

    @Test
    public void example03_part01_outputLargeNumberInMiddle() {
        long[] initialState = new long[] { 104, 1125899906842624L, 99 };
        long[] expectedOutputs = new long[] { initialState[1] };

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();
        long[] outputs = intCodeProgram.getAllOutput().toArray();
        System.out.println(Arrays.toString(outputs));
        assertArrayEquals("outputs", expectedOutputs, outputs);
    }

    @Test
    public void myPuzzleInput_part01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay09.txt");
        long expectedBoostKeycode = 2745604242L;

        IntCodeProgram boostProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgrammStr);
        long boostKeycode = AdventOfCodeDay09.runBoostProgramInTestMode(boostProgram);
        System.out.println("boostProgram output: " + Arrays.toString(boostProgram.getAllOutput().toArray()));
        assertEquals("boostKeycode", expectedBoostKeycode, boostKeycode);
    }


    @Test
    public void myPuzzleInput_part02() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay09.txt");
        long expectedBoostKeycode = 51135L;

        IntCodeProgram boostProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgrammStr);
        long boostKeycode = AdventOfCodeDay09.runBoostProgramInBoostMode(boostProgram);
        assertEquals("boostKeycode", expectedBoostKeycode, boostKeycode);
    }

}
