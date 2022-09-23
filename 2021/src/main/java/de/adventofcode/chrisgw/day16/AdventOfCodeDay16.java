package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.math.BigInteger;
import java.time.Year;
import java.util.ArrayList;
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
        return readBitsPacket(0).versionSum();
    }


    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return null;
    }


    public BitsPacket readBitsPacket(int beginIndex) {
        var packetHeader = BitsPacketHeader.parseHeader(binaryString);
        return switch (packetHeader.typeId()) {
            case 4 -> BitsLiteralPacket.readPacket(binaryString, beginIndex);
            default -> readBitsOperatorPacket(beginIndex);
        };
    }

    private BitsOperatorPacket readBitsOperatorPacket(int beginIndex) {
        BitsPacketHeader packetHeader = BitsPacketHeader.parseHeader(binaryString, beginIndex);

        boolean lengthTypeBit = binaryString.charAt(6) == '0';
        BigInteger totalLengthInBits;
        int subPacketBeginIndex;
        if (lengthTypeBit) {
            String substring = binaryString.substring(7, 7 + 15);
            totalLengthInBits = new BigInteger(substring, 2);
            subPacketBeginIndex = 7 + 15;
        } else {
            String substring = binaryString.substring(7, 7 + 11);
            totalLengthInBits = new BigInteger(substring, 2);
            subPacketBeginIndex = 7 + 11;
        }

        List<BitsPacket> subPackets = new ArrayList<>();
        return new BitsOperatorPacket(packetHeader, subPackets);
    }

    private String readBinaryString() {
        return inputLines()
                .findFirst()
                .map(hexPacketString -> new BigInteger(hexPacketString, 16))
                .map(hexNumber -> hexNumber.toString(2))
                .orElse("");
    }

}
