package de.adventofcode.chrisgw.day02;

import java.util.List;
import java.util.stream.Collectors;


/**
 * https://adventofcode.com/2021/day/2
 */
public class AdventOfCodeDay02 {

    private AdventOfCodeDay02() {

    }


    public static int part1(List<String> commandLines) {
        Submarine submarine = new Submarine();
        List<SubmarineCommand> commands = commandLines.stream()
                .map(SubmarineCommand::parseSubmarineCommand)
                .collect(Collectors.toList());
        submarine.executeCommands(commands);
        return submarine.getDepth() * submarine.getHorizontalPosition();
    }


}
