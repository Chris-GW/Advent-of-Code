package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/7">Advent of Code - day 7</a>
 */
public class AdventOfCodeDay07 extends AdventOfCodePuzzleSolver {

    private final FilesystemPath rootPath = new FilesystemPath();


    public AdventOfCodeDay07(List<String> inputLines) {
        super(Year.of(2022), 7, inputLines);
    }


    public Long solveFirstPart() {
        var filesystemTerminal = new FilesystemTerminal(rootPath, getInputLines());
        while (filesystemTerminal.hasNext()) {
            filesystemTerminal.runNextCommand();
        }
        return rootPath.allChildren()
                .filter(FilesystemPath::isDirectory)
                .mapToLong(FilesystemPath::getSize)
                .filter(size -> size < 100_000L)
                .sum();
    }


    public Long solveSecondPart() {
        var filesystemTerminal = new FilesystemTerminal(rootPath, getInputLines());
        while (filesystemTerminal.hasNext()) {
            filesystemTerminal.runNextCommand();
        }
        long totalAvailableSpace = 70_000_000L;
        long neededFreeSpace = 30_000_000L;
        long totalUsedSpace = rootPath.getSize();
        long currentFreeSpace = totalAvailableSpace - totalUsedSpace;
        return rootPath.allChildren()
                .filter(FilesystemPath::isDirectory)
                .mapToLong(FilesystemPath::getSize)
                .filter(directorySize -> currentFreeSpace + directorySize >= neededFreeSpace)
                .min()
                .orElse(0L);
    }

}
