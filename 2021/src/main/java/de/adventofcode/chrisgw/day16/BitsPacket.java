package de.adventofcode.chrisgw.day16;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.adventofcode.chrisgw.day16.BitsPacketType.LITERAL_VALUE;


@Data
public class BitsPacket {

    public static final int HEADER_PACKET_SIZE = 3;
    public static final int FIRST_LITERAL_PACKET_INDEX = 6;
    public static final int LITERAL_GROUP_SIZE = 5;

    private final String binaryString;
    private final int beginIndex;

    public BitsPacket(String binaryString) {
        this(binaryString, 0);
    }

    protected BitsPacket(String binaryString, int beginIndex) {
        this.binaryString = binaryString;
        this.beginIndex = beginIndex;
    }

    public List<BitsPacket> subPackets() {
        if (LITERAL_VALUE.equals(typeId())) {
            return Collections.emptyList();
        }
        int lengthTypeBitIndex = beginIndex + 6;
        int index = lengthTypeBitIndex + 1;
        boolean lengthTypeBit = binaryString.charAt(lengthTypeBitIndex) == '0';
        if (lengthTypeBit) {
            String substring = binaryString.substring(index, index + 15);
            long totalLengthInBits = new BigInteger(substring, 2).longValueExact();
            int subPacketBeginIndex = index + 15;
            return readSubPacketsWithTotalLengthInBits(subPacketBeginIndex, totalLengthInBits);
        } else {
            String substring = binaryString.substring(index, index + 11);
            long numberOfSubPackets = new BigInteger(substring, 2).longValueExact();
            int subPacketBeginIndex = index + 11;
            return readSubPackets(subPacketBeginIndex, numberOfSubPackets);
        }
    }

    private List<BitsPacket> readSubPacketsWithTotalLengthInBits(int subPacketBeginIndex, long totalLengthInBits) {
        List<BitsPacket> subPackets = new ArrayList<>();
        int beginIndex = subPacketBeginIndex;
        while (beginIndex < subPacketBeginIndex + totalLengthInBits) {
            BitsPacket subPacket = new BitsPacket(binaryString, beginIndex);
            subPackets.add(subPacket);
            beginIndex = subPacket.getEndIndex();
        }
        return subPackets;
    }

    private List<BitsPacket> readSubPackets(int subPacketBeginIndex, long numberOfSubPackets) {
        List<BitsPacket> subPackets = new ArrayList<>();
        int beginIndex = subPacketBeginIndex;
        for (long n = 0; n < numberOfSubPackets; n++) {
            BitsPacket subPacket = new BitsPacket(binaryString, beginIndex);
            subPackets.add(subPacket);
            beginIndex = subPacket.getEndIndex();
        }
        return subPackets;
    }


    public int getEndIndex() {
        if (LITERAL_VALUE.equals(typeId())) {
            return literalEndIndex();
        } else {
            List<BitsPacket> subPackets = subPackets();
            return subPackets.get(subPackets.size() - 1).getEndIndex();
        }
    }

    private int literalEndIndex() {
        int literalBeginIndex = beginIndex + FIRST_LITERAL_PACKET_INDEX;
        for (int i = literalBeginIndex; i < binaryString.length(); i += LITERAL_GROUP_SIZE) {
            boolean keepReadingBit = binaryString.charAt(i) == '1';
            if (!keepReadingBit) {
                return i + LITERAL_GROUP_SIZE;
            }
        }
        return literalBeginIndex;
    }


    public BigInteger literalValue() {
        BitsPacketType bitsPacketType = typeId();
        return subPackets()
                .stream()
                .map(BitsPacket::literalValue)
                .reduce(bitsPacketType.getReduceOperation())
                .orElseGet(this::parseLiteralValue);
    }

    private BigInteger parseLiteralValue() {
        StringBuilder literalBinaryString = new StringBuilder();
        int literalBeginIndex = beginIndex + FIRST_LITERAL_PACKET_INDEX;
        for (int i = literalBeginIndex; i < binaryString.length(); i += LITERAL_GROUP_SIZE) {
            boolean keepReadingBit = binaryString.charAt(i) == '1';
            int literalValueBeginIndex = i + 1;
            int literalValueEndIndex = i + LITERAL_GROUP_SIZE;
            literalBinaryString.append(binaryString, literalValueBeginIndex, literalValueEndIndex);
            if (!keepReadingBit) {
                break;
            }
        }
        return new BigInteger(literalBinaryString.toString(), 2);
    }


    public long versionSum() {
        return version() + subPackets().stream().mapToLong(BitsPacket::versionSum).sum();
    }


    public int version() {
        int headerEndIndex = beginIndex + HEADER_PACKET_SIZE;
        return Integer.parseInt(binaryString, beginIndex, headerEndIndex, 2);
    }

    public BitsPacketType typeId() {
        int headerBeginIndex = beginIndex + HEADER_PACKET_SIZE;
        int headerEndIndex = headerBeginIndex + HEADER_PACKET_SIZE;
        int typeId = Integer.parseInt(binaryString, headerBeginIndex, headerEndIndex, 2);
        return BitsPacketType.valueOf(typeId);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("beginIndex", beginIndex)
                .append("endIndex", getEndIndex())
                .append("version", version())
                .append("typeId", typeId())
                .append("literalValue", literalValue())
                .toString();
    }

}
