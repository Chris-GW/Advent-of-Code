package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day02.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay05Test {


    @Test
    public void example01() {
        int[] initialState = new int[] { 1002, 4, 3, 4, 33 };
        int[] expectedFinalMemoryState = new int[] { 1002, 4, 3, 4, 99 };

        IntCodeProgram intCodeProgram = new IntCodeProgram(initialState);
        while (intCodeProgram.hasNext()) {
            intCodeProgram.next();
        }
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


}
