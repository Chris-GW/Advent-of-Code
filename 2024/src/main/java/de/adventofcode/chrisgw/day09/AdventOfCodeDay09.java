package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.ArrayUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
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
            if (id != -1) {
                checksum += i * id;
            }
        }
        return checksum;
    }


    private static String printFileBlocks(int[] fileBlocks) {
        return Arrays.stream(fileBlocks)
                .mapToObj(id -> id == -1 ? "." : String.valueOf(id % 10))
                .collect(Collectors.joining());
    }


    @Override
    public Long solveSecondPart() {
        String diskMapString = inputLines().findFirst().orElseThrow();
        List<DiskMapRecord> diskMapRecords = parseDiskMapRecords(diskMapString);
        int[] fileBlocks = fillFileBlocks(diskMapRecords);
        compressFileBlocks2(fileBlocks);
        System.out.println(printFileBlocks(fileBlocks));
        return calculateFilesystemChecksum(fileBlocks);
    }

    private static void compressFileBlocks2(int[] fileBlocks) {
        int fileStartIndex = fileBlocks.length - 1;
        int previousFileId = Integer.MAX_VALUE;

        while (fileStartIndex >= 0) {
            // move to next fileBlock
            int fileId = fileBlocks[fileStartIndex];
            while (fileId == -1) {
                fileId = fileBlocks[--fileStartIndex];
            }

            // stop when fileId is not decreasing
            if (fileId > previousFileId) {
                fileStartIndex--;
                continue;
            }
            previousFileId = fileId;

            // measure fileSize and move fileStartIndex to beginning of this fileBlock
            int fileSize = 0;
            for (int i = fileStartIndex; i >= 0 && fileBlocks[i] == fileId; i--) {
                fileSize++;
            }
            fileStartIndex -= fileSize - 1;

            // find a fitting free space
            OptionalInt freeSpaceStartIndex = findFittingFreeSpace(fileBlocks, fileSize);
            if (freeSpaceStartIndex.isPresent() && freeSpaceStartIndex.getAsInt() < fileStartIndex) {
                ArrayUtils.swap(fileBlocks, freeSpaceStartIndex.getAsInt(), fileStartIndex, fileSize);
            }
            fileStartIndex--;
        }
    }

    private static OptionalInt findFittingFreeSpace(int[] fileBlocks, int fileSize) {
        for (int freeSpaceStartIndex = 0; freeSpaceStartIndex < fileBlocks.length; freeSpaceStartIndex++) {
            for (int length = 0; freeSpaceStartIndex + length < fileBlocks.length; length++) {
                if (length >= fileSize) {
                    // found fitting free space
                    return OptionalInt.of(freeSpaceStartIndex);
                } else if (fileBlocks[freeSpaceStartIndex + length] != -1) {
                    // free space to small
                    break;
                }
            }
        }
        return OptionalInt.empty();
    }

}
