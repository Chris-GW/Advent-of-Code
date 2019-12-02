package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay02Test {

    @Test
    public void myPuzzleInputPart01() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/day02/myPuzzleInput.txt");
        int expectedOutputValue = 7594646;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        int outputValue = adventOfCodeDay02.restoreGravityAssistProgram();
        assertEquals("restoreGravityAssistProgram", expectedOutputValue, outputValue);
    }

    @Test
    public void myPuzzleInputPart02() {
        String intCodeProgrammStr = TestUtils.readSingleLineOfClassPathResource("/day02/myPuzzleInput.txt");
        int whisedOutput = 19690720;
        int expectedResult = 3376;

        AdventOfCodeDay02 adventOfCodeDay02 = new AdventOfCodeDay02(intCodeProgrammStr);
        int result = adventOfCodeDay02.completeGravityAssistProgram(whisedOutput);
        assertEquals("restoreGravityAssistProgram", expectedResult, result);
    }

}
