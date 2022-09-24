package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.time.Year;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2021/day/16">https://adventofcode.com/2021/day/16</a>
 */
public class AdventOfCodeDay16 extends AdventOfCodePuzzleSolver<Long> {

    private final String binaryString;

    public AdventOfCodeDay16(List<String> inputLines) {
        super(Year.of(2021), 16, inputLines);
        binaryString = readBinaryString();
    }


    public Long solveFirstPart() {
        return readBitsPacket().versionSum();
    }

    public BitsPacket readBitsPacket() {
        return new BitsPacket(binaryString);
    }


    public Long solveSecondPart() {
        return readBitsPacket().literalValue().longValueExact();
    }


    private String readBinaryString() {
        return inputLines()
                .findFirst()
                .map(this::toBinaryString)
                .orElse("");
    }

    private String toBinaryString(String hexPacketString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hexPacketString.length(); i++) {
            BigInteger hexNumber = new BigInteger(hexPacketString.substring(i, i + 1), 16);
            String binaryString = hexNumber.toString(2);
            sb.append(StringUtils.leftPad(binaryString, 4, '0'));
        }
        return sb.toString();
    }

}
