package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;


/**
 * 2019 Day 11: Space Police
 * https://adventofcode.com/2019/day/11
 */
public class AdventOfCodeDay11 {


    public static int paintedPanelsCount(IntCodeProgram robotProgram) {
        HullPaintingRobot hullPaintingRobot = new HullPaintingRobot(robotProgram);
        hullPaintingRobot.run();
        return hullPaintingRobot.paintedPanelsCount();
    }


    public static String runPaintingRobotOnWhiteStartingPanel(IntCodeProgram robotProgram) {
        HullPaintingRobot hullPaintingRobot = HullPaintingRobot.paintingRobotOnWhiteStartingPanel(robotProgram);
        hullPaintingRobot.run();
        String hullIdentification = hullPaintingRobot.toString();
        System.out.println(hullIdentification);
        return hullIdentification;
    }

}
