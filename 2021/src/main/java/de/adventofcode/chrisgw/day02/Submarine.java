package de.adventofcode.chrisgw.day02;

import lombok.Data;

import java.util.List;


@Data
public class Submarine {

    private int depth;
    private int horizontalPosition;


    public void executeCommands(List<SubmarineCommand> commands) {
        commands.forEach(this::executeCommand);
    }

    private void executeCommand(SubmarineCommand submarineCommand) {
        SubmarineCommandType submarineCommandType = submarineCommand.commandType();
        int units = submarineCommand.units();
        depth += submarineCommandType.getDeltaDepth() * units;
        horizontalPosition += submarineCommandType.getDeltaHorizontal() * units;
    }


}
