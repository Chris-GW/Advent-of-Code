package de.adventofcode.chrisgw.day14;

import java.util.Arrays;


/**
 * <h1><a href="https://adventofcode.com/2017/day/14>Day 14: Disk Defragmentation</a></h1>
 * <pre>
 * Suddenly, a scheduled job activates the system's disk defragmenter. Were
 * the situation different, you might sit and watch it for a while, but today,
 * you just don't have that kind of time. It's soaking up valuable system
 * resources that are needed elsewhere, and so the only option is to help it
 * finish its task as soon as possible.
 *
 * The disk in question consists of a 128x128 grid; each square of the grid is
 * either free or used. On this disk, the state of the grid is tracked by the
 * bits in a sequence of knot hashes.
 *
 * A total of 128 knot hashes are calculated, each corresponding to a single
 * row in the grid; each hash contains 128 bits which correspond to individual
 * grid squares. Each bit of a hash indicates whether that square is free (0)
 * or used (1).
 *
 * The hash inputs are a key string (your puzzle input), a dash, and a number
 * from 0 to 127 corresponding to the row. For example, if your key string
 * were flqrgnkx, then the first row would be given by the bits of the knot
 * hash of flqrgnkx-0, the second row from the bits of the knot hash of
 * flqrgnkx-1, and so on until the last row, flqrgnkx-127.
 *
 * The output of a knot hash is traditionally represented by 32 hexadecimal
 * digits; each of these digits correspond to 4 bits, for a total of
 * 4 * 32 = 128 bits. To convert to bits, turn each hexadecimal digit to its
 * equivalent binary value, high-bit first: 0 becomes 0000, 1 becomes 0001, e
 * becomes 1110, f becomes 1111, and so on; a hash that begins with a0c2017...
 * in hexadecimal would begin with 10100000110000100000000101110000... in binary.
 *
 * Continuing this process, the first 8 rows and columns for key flqrgnkx
 * appear as follows, using # to denote used squares, and . to denote free ones:
 *
 * ##.#.#..-->
 * .#.#.#.#
 * ....#.#.
 * #.#.##.#
 * .##.#...
 * ##..#..#
 * .#...#..
 * ##.#.##.-->
 * |      |
 * V      V
 *
 * In this example, 8108 squares are used across the entire 128x128 grid.
 *
 * Given your actual key string, how many squares are used?
 *
 * --- Part Two ---
 *
 * Now, all the defragmenter needs to know is the number of regions. A region
 * is a group of used squares that are all adjacent, not including diagonals.
 * Every used square is in exactly one region: lone used squares form their
 * own isolated regions, while several adjacent squares all count as a single region.
 *
 * In the example above, the following nine regions are visible, each marked
 * with a distinct digit:
 *
 * 11.2.3..-->
 * .1.2.3.4
 * ....5.6.
 * 7.8.55.9
 * .88.5...
 * 88..5..8
 * .8...8..
 * 88.8.88.-->
 * |      |
 * V      V
 *
 * Of particular interest is the region marked 8; while it does not appear
 * contiguous in this small view, all of the squares marked 8 are connected
 * when considering the whole 128x128 grid. In total, in this example, 1242
 * regions are present.
 *
 * How many regions are present given your key string?
 * </pre>
 */
public class DiskDefragmentation {

    private String diskKey;
    private DiskRowKnotHash[] diskRows = new DiskRowKnotHash[128];


    public DiskDefragmentation(String diskKey) {
        this.diskKey = diskKey;
        for (int rowIndex = 0; rowIndex < diskRows.length; rowIndex++) {
            String diskKnotHashLine = diskKey + "-" + rowIndex;
            diskRows[rowIndex] = DiskRowKnotHash.parseDiskRowKnotHash(diskKnotHashLine);
        }
    }


    public int countUsedSpace() {
        return Arrays.stream(diskRows).mapToInt(DiskRowKnotHash::countUsedSpace).sum();
    }


    public boolean isUsed(int x, int y) {
        return diskRows[y].diskRowUsage[x];
    }


    public int countDiskSpaceRegions() {
        int regionCount = 0;
        boolean[][] alreadyPartOfRegion = new boolean[128][128];

        for (int y = 0; y < diskRows.length; y++) {
            for (int x = 0; x < diskRows[y].diskRowUsage.length; x++) {
                if (isNewDiskSpaceRegion(x, y, alreadyPartOfRegion)) {
                    regionCount++;
                }
            }
        }
        return regionCount;
    }

    private boolean isNewDiskSpaceRegion(int x, int y, boolean[][] alreadyPartOfRegion) {
        if (!isValidDiskSpaceKoordinate(x, y) || alreadyPartOfRegion[y][x] || !isUsed(x, y)) {
            return false;
        }
        alreadyPartOfRegion[y][x] = true;
        isNewDiskSpaceRegion(x + 1, y, alreadyPartOfRegion);
        isNewDiskSpaceRegion(x - 1, y, alreadyPartOfRegion);
        isNewDiskSpaceRegion(x, y + 1, alreadyPartOfRegion);
        isNewDiskSpaceRegion(x, y - 1, alreadyPartOfRegion);
        return true;
    }

    private boolean isValidDiskSpaceKoordinate(int x, int y) {
        return (0 <= x && x < diskRows.length) && (0 <= y && y < diskRows.length);
    }


    public DiskRowKnotHash[] getDiskRows() {
        return diskRows;
    }

    public String getDiskKey() {
        return diskKey;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DiskRowKnotHash diskRow : diskRows) {
            sb.append(diskRow).append("\n");
        }
        return sb.toString();
    }

}
