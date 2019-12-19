package de.adventofcode.chrisgw.day19;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay19Test {

    @Test
    public void myPuzzleInput_part01_countAffectedCointsForGrid() {
        String intCodeProgramStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay19.txt");
        IntCodeProgram droneProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgramStr);
        int expectedAffectedPoints = 234;

        AdventOfCodeDay19 aocDay19 = new AdventOfCodeDay19(droneProgram);
        long affectedPoints = aocDay19.countAffectedCointsForGrid(50);
        System.out.println(aocDay19);
        assertEquals("affectedPoints", expectedAffectedPoints, affectedPoints);
    }

}
