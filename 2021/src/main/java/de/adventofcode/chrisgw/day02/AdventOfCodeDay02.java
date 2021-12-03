package de.adventofcode.chrisgw.day02;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * https://adventofcode.com/2021/day/2
 */
public class AdventOfCodeDay02 extends AdventOfCodePuzzleSolver<Integer> {

    public AdventOfCodeDay02(List<String> commandLines) {
        super(Year.of(2021), 2, commandLines);
    }


    @Override
    public Integer solveFirstPart() {
        Submarine submarine = new Submarine();
        List<SubmarineCommand> commands = inputLinesAsCommands();
        submarine.executeCommands(commands, false);
        return submarine.getDepth() * submarine.getHorizontalPosition();
    }

    @Override
    public Integer solveSecondPart() {
        Submarine submarine = new Submarine();
        List<SubmarineCommand> commands = inputLinesAsCommands();
        submarine.executeCommands(commands, true);
        return submarine.getDepth() * submarine.getHorizontalPosition();
    }

    private List<SubmarineCommand> inputLinesAsCommands() {
        return inputLines().map(SubmarineCommand::parseSubmarineCommand).toList();
    }

}
