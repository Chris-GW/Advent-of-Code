package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay12Test {

    @Test
    public void example01_part01_totalEnergyInSystem() {
        List<String> moonLocations = List.of( //
                "<x=-1, y=0, z=2>", //
                "<x=2, y=-10, z=-7>", //
                "<x=4, y=-8, z=8>", //
                "<x=3, y=5, z=-1>");
        int expectedTotalEnergyInSystem = 36 + 45 + 80 + 18; // 179
        int timeSteps = 10;

        AdventOfCodeDay12 moonSystem = AdventOfCodeDay12.parseMoonPositions(moonLocations);
        System.out.println(moonSystem);
        for (int step = 0; step < timeSteps; step++) {
            moonSystem.nextTimeStep();
            System.out.println(moonSystem);
        }

        int totalEnergyInSystem = moonSystem.totalEnergyInSystem();
        assertEquals("totalEnergyInSystem", expectedTotalEnergyInSystem, totalEnergyInSystem);
    }

    @Test
    public void example02_part01_totalEnergyInSystem() {
        List<String> moonLocations = List.of( //
                "<x=-8, y=-10, z=0>", //
                "<x=5, y=5, z=10>", //
                "<x=2, y=-7, z=3>", //
                "<x=9, y=-8, z=-3>");
        int timeSteps = 100;
        int expectedTotalEnergyInSystem = 290 + 608 + 574 + 468; // 1940

        AdventOfCodeDay12 moonSystem = AdventOfCodeDay12.parseMoonPositions(moonLocations);
        System.out.println(moonSystem);
        for (int step = 0; step < timeSteps; step++) {
            moonSystem.nextTimeStep();
            if (step % 10 == 9) {
                System.out.println(moonSystem);
            }
        }

        int totalEnergyInSystem = moonSystem.totalEnergyInSystem();
        assertEquals("totalEnergyInSystem", expectedTotalEnergyInSystem, totalEnergyInSystem);
    }


    @Test
    public void myPuzzleInput_part01_totalEnergyInSystem() {
        List<String> moonLocations = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        int expectedTotalEnergyInSystem = 10189;
        int timeSteps = 1_000;

        AdventOfCodeDay12 moonSystem = AdventOfCodeDay12.parseMoonPositions(moonLocations);
        System.out.println(moonSystem);
        for (int step = 0; step < timeSteps; step++) {
            moonSystem.nextTimeStep();
            if (step % 100 == 9) {
                System.out.println(moonSystem);
            }
        }

        int totalEnergyInSystem = moonSystem.totalEnergyInSystem();
        assertEquals("totalEnergyInSystem", expectedTotalEnergyInSystem, totalEnergyInSystem);
    }


    // part02

    @Test
    public void example01_part02_findNeededStepsForSystemSameState() {
        List<String> moonLocations = List.of( //
                "<x=-1, y=0, z=2>", //
                "<x=2, y=-10, z=-7>", //
                "<x=4, y=-8, z=8>", //
                "<x=3, y=5, z=-1>");
        long expectedNeededStepsForSystemSameState = 2772;

        AdventOfCodeDay12 moonSystem = AdventOfCodeDay12.parseMoonPositions(moonLocations);
        long neededStepsForSystemSameState = moonSystem.findNeededStepsForSystemSameState();
        assertEquals("neededStepsForSystemSameState", expectedNeededStepsForSystemSameState,
                neededStepsForSystemSameState);
    }

    @Test
    public void example02_part02_findNeededStepsForSystemSameState() {
        List<String> moonLocations = List.of( //
                "<x=-8, y=-10, z=0>", //
                "<x=5, y=5, z=10>", //
                "<x=2, y=-7, z=3>", //
                "<x=9, y=-8, z=-3>");
        long expectedNeededStepsForSystemSameState = 4686774924L;

        AdventOfCodeDay12 moonSystem = AdventOfCodeDay12.parseMoonPositions(moonLocations);
        long neededStepsForSystemSameState = moonSystem.findNeededStepsForSystemSameState();
        assertEquals("neededStepsForSystemSameState", expectedNeededStepsForSystemSameState,
                neededStepsForSystemSameState);
    }

    @Test
    public void myPuzzleInput_part02_findNeededStepsForSystemSameState() {
        List<String> moonLocations = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay12.txt");
        long expectedNeededStepsForSystemSameState = 469671086427712L;

        AdventOfCodeDay12 moonSystem = AdventOfCodeDay12.parseMoonPositions(moonLocations);
        long neededStepsForSystemSameState = moonSystem.findNeededStepsForSystemSameState();
        assertEquals("neededStepsForSystemSameState", expectedNeededStepsForSystemSameState,
                neededStepsForSystemSameState);
    }


}
