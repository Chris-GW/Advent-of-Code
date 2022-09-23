package de.adventofcode.chrisgw.day16;

import lombok.Getter;

import java.math.BigInteger;
import java.util.Collections;

import static java.util.Objects.requireNonNull;

public class BitsLiteralPacket extends BitsPacket {

    public static final int FIRST_LITERAL_PACKET_INDEX = 6;
    public static final int LITERAL_GROUP_SIZE = 5;

    @Getter
    private final BigInteger literalValue;


    public BitsLiteralPacket(BitsPacketHeader packetHeader, BigInteger literalValue) {
        super(packetHeader, Collections.emptyList());
        this.literalValue = requireNonNull(literalValue);
    }


    public static BitsLiteralPacket readPacket(String binaryString, int beginIndex) {
        BigInteger literalValue = parseLiteralValue(binaryString, beginIndex);
        BitsPacketHeader packetHeader = BitsPacketHeader.parseHeader(binaryString, beginIndex);
        return new BitsLiteralPacket(packetHeader, literalValue);
    }

    private static BigInteger parseLiteralValue(String binaryString, int beginIndex) {
        StringBuilder literalBinaryString = new StringBuilder();
        for (int i = beginIndex + FIRST_LITERAL_PACKET_INDEX;
             i < binaryString.length();
             i += LITERAL_GROUP_SIZE) {
            boolean keepReadingBit = binaryString.charAt(i) == '1';
            literalBinaryString.append(binaryString, i + 1, i + 5);
            if (!keepReadingBit) {
                return new BigInteger(literalBinaryString.toString(), 2);
            }
        }
        throw new IllegalArgumentException("could not parse literal value: " + binaryString);
    }


}
