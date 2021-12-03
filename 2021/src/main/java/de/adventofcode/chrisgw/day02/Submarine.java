package de.adventofcode.chrisgw.day02;

import lombok.Data;

import java.util.List;


@Data
public class Submarine {

    private int depth = 0;
    private int horizontalPosition = 0;
    private int aim = 0;


    public void executeCommands(List<SubmarineCommand> commands, boolean aimModus) {
        if (aimModus) {
            commands.forEach(this::executeAimCommand);
        } else {
            commands.forEach(this::executeCommand);
        }
    }

    private void executeCommand(SubmarineCommand submarineCommand) {
        SubmarineCommandType submarineCommandType = submarineCommand.commandType();
        int units = submarineCommand.units();
        depth += submarineCommandType.getDeltaDepth() * units;
        horizontalPosition += submarineCommandType.getDeltaHorizontal() * units;
    }

    private void executeAimCommand(SubmarineCommand submarineCommand) {
        SubmarineCommandType submarineCommandType = submarineCommand.commandType();
        int units = submarineCommand.units();

        switch (submarineCommandType) {
        case UP, DOWN -> aim += submarineCommandType.getDeltaDepth() * units;
        case FORWARD -> {
            depth += aim * units;
            horizontalPosition += submarineCommandType.getDeltaHorizontal() * units;
        }
        default -> throw new IllegalArgumentException("unknown commandType: " + submarineCommandType);
        }
    }

}
