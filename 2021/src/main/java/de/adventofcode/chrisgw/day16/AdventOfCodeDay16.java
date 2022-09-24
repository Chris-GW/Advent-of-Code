package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.math.BigInteger;
import java.time.Year;
import java.util.List;

/**
 * https://adventofcode.com/2021/day/16
 */
public class AdventOfCodeDay16 extends AdventOfCodePuzzleSolver<Integer> {

    private final String binaryString;

    public AdventOfCodeDay16(List<String> inputLines) {
        super(Year.of(2021), 16, inputLines);
        binaryString = readBinaryString();
    }


    public Integer solveFirstPart() {
        return readBitsPacket().versionSum();
    }

    public BitsPacket readBitsPacket() {
        return new BitsPacket(binaryString);
    }


    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return null;
    }


    private String readBinaryString() {
        return inputLines()
                .findFirst()
                .map(this::toBinaryString)
                .orElse("");
    }

    private String toBinaryString(String hexPacketString) {
        BigInteger hexNumber = new BigInteger(hexPacketString, 16);
        String binaryString = hexNumber.toString(2);
        return switch (hexPacketString.charAt(0)) {
            case '1' -> "000" + binaryString;
            case '2', '3' -> "00" + binaryString;
            case '4', '5', '6', '7' -> "0" + binaryString;
            default -> binaryString;
        };
    }

}
