package de.adventofcode.chrisgw.day02;

import java.util.List;


/**
 * https://adventofcode.com/2021/day/2
 */
public class AdventOfCodeDay02 {

    private AdventOfCodeDay02() {

    }


    public static int part1(List<String> commandLines) {
        Submarine submarine = new Submarine();
        List<SubmarineCommand> commands = commandLines.stream().map(SubmarineCommand::parseSubmarineCommand).toList();
        submarine.executeCommands(commands, false);
        return submarine.getDepth() * submarine.getHorizontalPosition();
    }


    public static int part2(List<String> commandLines) {
        Submarine submarine = new Submarine();
        List<SubmarineCommand> commands = commandLines.stream().map(SubmarineCommand::parseSubmarineCommand).toList();
        submarine.executeCommands(commands, true);
        return submarine.getDepth() * submarine.getHorizontalPosition();
    }

}
