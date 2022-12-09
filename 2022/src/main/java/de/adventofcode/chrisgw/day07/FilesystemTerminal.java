package de.adventofcode.chrisgw.day07;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;


public class FilesystemTerminal {

    private final FilesystemPath rootPath;
    private FilesystemPath currentPath;

    private final List<String> terminalLines;
    private int lineIndex = 0;


    public FilesystemTerminal(FilesystemPath rootPath, List<String> terminalLines) {
        this.rootPath = requireNonNull(rootPath);
        this.currentPath = rootPath;
        this.terminalLines = new ArrayList<>(terminalLines);
    }


    public boolean runNextCommand() {
        if (!hasNext()) {
            throw new IllegalStateException("has no more commands");
        } else if (!isCommand()) {
            throw new IllegalStateException("next terminalLine is no command: " + currentLine());
        }

        FilesystemCommand filesystemCommand = FilesystemCommand.parseFilesystemCommand(currentLine());
        switch (filesystemCommand) {
        case CHANGE_DIRECTORY -> runChangeDirectoryCommand();
        case LIST -> runListCommand();
        default -> throw new IllegalStateException("Unexpected command: " + currentLine());
        }
        return hasNext();
    }


    private void runChangeDirectoryCommand() {
        String commandString = currentLine();
        String changePathString = commandString.substring("$ cd ".length());
        if (changePathString.equals("/")) {
            currentPath = rootPath;
        } else if (changePathString.equals("..")) {
            currentPath = currentPath.getParent();
        } else {
            currentPath = currentPath.findDirectory(changePathString);
        }
        lineIndex++;
    }


    private void runListCommand() {
        lineIndex++;
        while (hasNext() && !isCommand()) {
            String outputLine = currentLine();
            if (outputLine.startsWith("dir ")) {
                String directoryName = outputLine.substring("dir ".length());
                currentPath.addDirectory(directoryName);
            } else {
                int separatorIndex = outputLine.indexOf(" ");
                long fileSize = Long.parseLong(outputLine, 0, separatorIndex, 10);
                String fileName = outputLine.substring(separatorIndex + 1);
                currentPath.addFile(fileName, fileSize);
            }
            lineIndex++;
        }
    }

    private boolean isCommand() {
        return currentLine().startsWith("$");
    }


    public boolean hasNext() {
        return lineIndex < terminalLines.size();
    }


    public String currentLine() {
        return terminalLines.get(lineIndex);
    }


}
