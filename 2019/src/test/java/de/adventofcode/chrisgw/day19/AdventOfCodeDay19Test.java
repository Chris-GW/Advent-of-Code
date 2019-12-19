package de.adventofcode.chrisgw.day19;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay19Test {

    @Test
    public void myPuzzleInput_part01_countAffectedPointsForGrid() {
        String intCodeProgramStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay19.txt");
        IntCodeProgram droneProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgramStr);
        long expectedAffectedPoints = 234;

        AdventOfCodeDay19 aocDay19 = new AdventOfCodeDay19(droneProgram);
        long affectedPoints = aocDay19.countAffectedPointsForGrid(50);
        assertEquals("affectedPoints", expectedAffectedPoints, affectedPoints);
    }


    @Test
    public void myPuzzleInput_part02_findSantaShip() {
        String intCodeProgramStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay19.txt");
        IntCodeProgram droneProgram = IntCodeProgram.parseIntCodeProgram(intCodeProgramStr);
        int shipSize = 100;
        long expectedLocation = 9330815; // to high

        AdventOfCodeDay19 aocDay19 = new AdventOfCodeDay19(droneProgram);
        long location = aocDay19.findShip(shipSize);
        System.out.println(aocDay19);
        assertEquals("location", expectedLocation, location);
    }

}
