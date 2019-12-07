package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day02.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay05Test {


    @Test
    public void example01_part01() {
        int[] initialState = new int[] { 1002, 4, 3, 4, 33 };
        int[] expectedFinalMemoryState = new int[] { 1002, 4, 3, 4, 99 };

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.run();
        assertArrayEquals("memory", expectedFinalMemoryState, intCodeProgram.getMemory());
    }


    @Test
    public void myPuzzleInputPart01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay05.txt");
        int expectedDignosticCode = 5044655;

        AdventOfCodeDay05 adventOfCodeDay05 = new AdventOfCodeDay05(intCodeProgrammStr);
        int dignosticCode = adventOfCodeDay05.runTestAndReturnDignosticCode();
        assertEquals("runTestAndReturnDignosticCode", expectedDignosticCode, dignosticCode);
    }


    // part 02

    // example 02 equalTo

    @Test
    public void example02_equalTo_8_positionMode_true() {
        int[] initialState = new int[] { 3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8 };
        int expectedOutput = 1;
        int input = 8;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example02_equalTo_8_positionMode_false() {
        int[] initialState = new int[] { 3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8 };
        int expectedOutput = 0;
        int input = 7;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }


    @Test
    public void example02_equalTo_8_immediateMode_true() {
        int[] initialState = new int[] { 3, 3, 1108, -1, 8, 3, 4, 3, 99 };
        int expectedOutput = 1;
        int input = 8;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example02_equalTo_8_immediateMode_false() {
        int[] initialState = new int[] { 3, 3, 1108, -1, 8, 3, 4, 3, 99 };
        int expectedOutput = 0;
        int input = 7;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }


    // example 03 lessThan

    @Test
    public void example03_lessThan_8_positionMode_true() {
        int[] initialState = new int[] { 3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8 };
        int expectedOutput = 1;
        int input = 7;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example03_lessThan_8_positionMode_false() {
        int[] initialState = new int[] { 3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8 };
        int expectedOutput = 0;
        int input = 9;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }


    @Test
    public void example03_lessThan_8_immediateMode_true() {
        int[] initialState = new int[] { 3, 3, 1107, -1, 8, 3, 4, 3, 99 };
        int expectedOutput = 1;
        int input = 7;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example03_lessThan_8_immediateMode_false() {
        int[] initialState = new int[] { 3, 3, 1107, -1, 8, 3, 4, 3, 99 };
        int expectedOutput = 0;
        int input = 9;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }


    // example 04

    @Test
    public void example04_equalsTo_1_positionMode_true() {
        int[] initialState = new int[] { 3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9 };
        int expectedOutput = 0;
        int input = 0;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example04_equalsTo_1_positionMode_false() {
        int[] initialState = new int[] { 3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9 };
        int expectedOutput = 1;
        int input = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }


    @Test
    public void example04_equalsTo_1_immediateMode_true() {
        int[] initialState = new int[] { 3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1 };
        int expectedOutput = 0;
        int input = 0;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example04_equalsTo_1_immediateMode_false() {
        int[] initialState = new int[] { 3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1 };
        int expectedOutput = 1;
        int input = 2;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }


    // example 05

    @Test
    public void example05_largeExample_below_8() {
        int[] initialState = new int[] { 3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, //
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, //
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99 };
        int expectedOutput = 999;
        int input = 7;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example05_largeExample_equal_8() {
        int[] initialState = new int[] { 3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, //
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, //
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99 };
        int expectedOutput = 1000;
        int input = 8;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }

    @Test
    public void example05_largeExample_greater_8() {
        int[] initialState = new int[] { 3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, //
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, //
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99 };
        int expectedOutput = 1001;
        int input = 9;

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        intCodeProgram.addInput(input);
        intCodeProgram.run();
        assertEquals("lastOutput", expectedOutput, intCodeProgram.lastOutput());
    }


    @Test
    public void myPuzzleInputPart02() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay05.txt");
        int systemId = 5;
        int expectedDignosticCode = 7408802;

        AdventOfCodeDay05 adventOfCodeDay05 = new AdventOfCodeDay05(intCodeProgrammStr);
        int dignosticCode = adventOfCodeDay05.runTestAndReturnDignosticCode(systemId);
        assertEquals("runTestAndReturnDignosticCode", expectedDignosticCode, dignosticCode);
    }

}
