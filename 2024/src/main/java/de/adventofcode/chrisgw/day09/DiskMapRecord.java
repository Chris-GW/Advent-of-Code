package de.adventofcode.chrisgw.day09;

import java.util.Arrays;


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
