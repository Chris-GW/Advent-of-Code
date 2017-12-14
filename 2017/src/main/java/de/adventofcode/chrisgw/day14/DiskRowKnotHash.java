package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.day10.KnotHash;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DiskRowKnotHash {

    private static final Pattern DISK_KNOT_HASH_LINE_PATTERN = Pattern.compile("(\\w+)\\-(\\d+)");

    public int row;
    public String diskRowKnotHash;
    public boolean[] diskRowUsage = new boolean[128];


    public DiskRowKnotHash(int row, String diskRowKnotHash) {
        this.row = row;
        this.diskRowKnotHash = diskRowKnotHash;
        decodeDiskRowKnotHash();
    }

    public static DiskRowKnotHash parseDiskRowKnotHash(String diskKnotHashLine) {
        Matcher diskKnotHashLineMatcher = DISK_KNOT_HASH_LINE_PATTERN.matcher(diskKnotHashLine);
        if (!diskKnotHashLineMatcher.matches()) {
            throw new IllegalArgumentException(
                    String.format("Given diskKnoteHashLine doesn't match needed Pattern '%s': %s",
                            DISK_KNOT_HASH_LINE_PATTERN.pattern(), diskKnotHashLine));
        }

        MatchResult diskKnotHashLineMatchResult = diskKnotHashLineMatcher.toMatchResult();
        return parseDiskRowKnotHashOfMatchResult(diskKnotHashLineMatchResult);
    }

    private static DiskRowKnotHash parseDiskRowKnotHashOfMatchResult(MatchResult diskKnotHashLineMatchResult) {
        String diskKnotHash = diskKnotHashLineMatchResult.group(0);
        int diskRow = Integer.parseInt(diskKnotHashLineMatchResult.group(2));
        return new DiskRowKnotHash(diskRow, diskKnotHash);
    }


    private void decodeDiskRowKnotHash() {
        String knotHash = KnotHash.calculateKnotHash(diskRowKnotHash);
        BigInteger hexadecimal = new BigInteger(knotHash, 16);
        String hexadecimalBinaryStr = hexadecimal.toString(2);

        int offset = 128 - hexadecimalBinaryStr.length();
        for (int i = 0; i < hexadecimalBinaryStr.length(); i++) {
            diskRowUsage[i + offset] = hexadecimalBinaryStr.charAt(i) == '1';
        }
    }


    public int countUsedSpace() {
        int usedSpaceCount = 0;
        for (int i = 0; i < diskRowUsage.length; i++) {
            boolean isUsed = diskRowUsage[i];
            if (isUsed) {
                usedSpaceCount++;
            }
        }
        return usedSpaceCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DiskRowKnotHash))
            return false;

        DiskRowKnotHash that = (DiskRowKnotHash) o;

        if (row != that.row)
            return false;
        if (diskRowKnotHash != null ? !diskRowKnotHash.equals(that.diskRowKnotHash) : that.diskRowKnotHash != null)
            return false;
        return Arrays.equals(diskRowUsage, that.diskRowUsage);
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + (diskRowKnotHash != null ? diskRowKnotHash.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(diskRowUsage);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-12s = ", diskRowKnotHash));
        for (boolean used : diskRowUsage) {
            if (used) {
                sb.append('#');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }

}
