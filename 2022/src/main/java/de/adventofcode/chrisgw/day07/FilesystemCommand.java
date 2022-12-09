package de.adventofcode.chrisgw.day07;

import lombok.Getter;

import java.util.Arrays;


public enum FilesystemCommand {

    CHANGE_DIRECTORY("cd"), LIST("ls");


    @Getter
    private final String commandName;


    FilesystemCommand(String commandName) {
        this.commandName = commandName;
    }

    public static FilesystemCommand parseFilesystemCommand(String commandString) {
        return Arrays.stream(FilesystemCommand.values())
                .filter(filesystemCommand -> filesystemCommand.matchCommand(commandString))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException("Could not find FilesystemCommand for: " + commandString));
    }


    public boolean matchCommand(String commandString) {
        return commandString.startsWith("$ " + getCommandName());
    }


    @Override
    public String toString() {
        return getCommandName();
    }

}
