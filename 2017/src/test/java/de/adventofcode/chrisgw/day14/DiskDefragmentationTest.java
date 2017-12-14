package de.adventofcode.chrisgw.day14;

import org.junit.Assert;
import org.junit.Test;


public class DiskDefragmentationTest {


    @Test
    public void diskDefragmentation_example() {
        String diskKey = "flqrgnkx";
        boolean[][] expectedDiskUsage = new boolean[][] { //
                diskUsageStringToBooleans("##.#.#.."), //
                diskUsageStringToBooleans(".#.#.#.#"), //
                diskUsageStringToBooleans("....#.#."), //
                diskUsageStringToBooleans("#.#.##.#"), //
                diskUsageStringToBooleans(".##.#..."), //
                diskUsageStringToBooleans("##..#..#"), //
                diskUsageStringToBooleans(".#...#.."), //
                diskUsageStringToBooleans("##.#.##.") };
        int expectedUsedSpaceCount = 8108;

        DiskDefragmentation diskDefragmentation = new DiskDefragmentation(diskKey);
        int usedSpaceCount = diskDefragmentation.countUsedSpace();
        System.out.println(diskDefragmentation);

        DiskRowKnotHash[] diskRows = diskDefragmentation.getDiskRows();
        for (int rowIndex = 0; rowIndex < expectedDiskUsage.length; rowIndex++) {
            boolean[] rowUsage = expectedDiskUsage[rowIndex];
            DiskRowKnotHash diskRow = diskRows[rowIndex];
            for (int columnIndex = 0; columnIndex < rowUsage.length; columnIndex++) {
                boolean isUsed = diskRow.diskRowUsage[columnIndex];
                boolean expectIsUsed = rowUsage[columnIndex];
                if (expectIsUsed) {
                    Assert.assertTrue(rowIndex + ";" + columnIndex, isUsed);
                } else {
                    Assert.assertFalse(rowIndex + ";" + columnIndex, isUsed);
                }
            }
        }

        Assert.assertEquals("Expect used space", expectedUsedSpaceCount, usedSpaceCount);
    }


    @Test
    public void diskDefragmentation_myTask() {
        String diskKey = "wenycdww";
        int expectedUsedSpaceCount = 8226;

        DiskDefragmentation diskDefragmentation = new DiskDefragmentation(diskKey);
        int usedSpaceCount = diskDefragmentation.countUsedSpace();
        System.out.println(diskDefragmentation);

        Assert.assertEquals("Expect used space", expectedUsedSpaceCount, usedSpaceCount);
    }


    private boolean[] diskUsageStringToBooleans(String diskUsageStr) {
        boolean[] usage = new boolean[diskUsageStr.length()];
        for (int i = 0; i < diskUsageStr.length(); i++) {
            usage[i] = diskUsageStr.charAt(i) == '#';
        }
        return usage;
    }

}