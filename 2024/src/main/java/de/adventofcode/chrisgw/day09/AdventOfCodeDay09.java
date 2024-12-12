package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.ArrayUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <a href="https://adventofcode.com/2024/day/9">Advent of Code - day 9</a>
 */
public class AdventOfCodeDay09 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay09(List<String> inputLines) {
        super(Year.of(2024), 9, inputLines);
    }


    @Override
    public Long solveFirstPart() {
        String diskMapString = inputLines().findFirst().orElseThrow();
        List<DiskMapRecord> diskMapRecords = parseDiskMapRecords(diskMapString);
        int[] fileBlocks = fillFileBlocks(diskMapRecords);
        System.out.println(printFileBlocks(fileBlocks));
        compressFileBlocks(fileBlocks);
        System.out.println(printFileBlocks(fileBlocks));
        return calculateFilesystemChecksum(fileBlocks);
    }

    private static List<DiskMapRecord> parseDiskMapRecords(String diskMapString) {
        List<DiskMapRecord> diskMapRecords = new ArrayList<>(diskMapString.length() / 2);
        int id = 0;
        for (int i = 0; i < diskMapString.length(); i++) {
            int fileLength = Character.getNumericValue(diskMapString.charAt(i));
            int freeSpace = 0;
            if (i + 1 < diskMapString.length()) {
                freeSpace = Character.getNumericValue(diskMapString.charAt(i + 1));
                i++;
            }
            DiskMapRecord diskMapRecord = new DiskMapRecord(id++, fileLength, freeSpace);
            diskMapRecords.add(diskMapRecord);
        }
        return diskMapRecords;
    }


    private static int[] fillFileBlocks(List<DiskMapRecord> diskMapRecords) {
        int fileBlockSize = diskMapRecords.stream().mapToInt(DiskMapRecord::blockLength).sum();
        int[] fileBlocks = new int[fileBlockSize];

        int fileStart = 0;
        for (DiskMapRecord diskMapRecord : diskMapRecords) {
            diskMapRecord.fillBlocks(fileBlocks, fileStart);
            fileStart += diskMapRecord.blockLength();
        }
        return fileBlocks;
    }


    private static void compressFileBlocks(int[] fileBlocks) {
        int leftIndex = 0;
        int rightIndex = fileBlocks.length - 1;
        while (leftIndex < rightIndex) {
            if (fileBlocks[leftIndex] == -1) {
                while (fileBlocks[rightIndex] == -1) {
                    rightIndex--;
                }
                ArrayUtils.swap(fileBlocks, leftIndex, rightIndex);
            }
            leftIndex++;
        }
    }


    private static long calculateFilesystemChecksum(int[] fileBlocks) {
        long checksum = 0L;
        for (int i = 0; i < fileBlocks.length; i++) {
            long id = fileBlocks[i];
            if (id == -1) {
                break;
            }
            checksum += i * id;
        }
        return checksum;
    }


    private static String printFileBlocks(int[] fileBlocks) {
        return Arrays.stream(fileBlocks)
                .mapToObj(id -> id == -1 ? "." : String.valueOf(id % 10))
                .collect(Collectors.joining());
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    record DiskMapRecord(int id, int fileLength, int freeSpace) {

        int blockLength() {
            return fileLength() + freeSpace();
        }

        public void fillBlocks(int[] fileBlocks, int fileStart) {
            int fileLengthEnd = fileStart + fileLength;
            int freeSpaceEnd = fileLengthEnd + freeSpace;
            Arrays.fill(fileBlocks, fileStart, fileLengthEnd, id);
            Arrays.fill(fileBlocks, fileLengthEnd, freeSpaceEnd, -1);
        }

    }

}
