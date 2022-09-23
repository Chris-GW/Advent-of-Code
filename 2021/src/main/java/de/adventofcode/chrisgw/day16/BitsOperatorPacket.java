package de.adventofcode.chrisgw.day16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BitsOperatorPacket extends BitsPacket {


    public BitsOperatorPacket(BitsPacketHeader packetHeader, List<BitsPacket> subPackets) {
        super(packetHeader, subPackets);
    }


    public static BitsOperatorPacket readPacket(String hexPacketString) {
        BigInteger hexNumber = new BigInteger(hexPacketString, 16);
        String binaryString = hexNumber.toString(2);
        BitsPacketHeader packetHeader = BitsPacketHeader.parseHeader(binaryString);

        boolean lengthTypeBit = binaryString.charAt(6) == '0';
        BigInteger totalLengthInBits;
        int subPacketBeginIndex;
        if (lengthTypeBit) {
            totalLengthInBits = new BigInteger(binaryString.substring(7, 7 + 15), 2);
            subPacketBeginIndex = 7 + 15;
        } else {
            totalLengthInBits = new BigInteger(binaryString.substring(7, 7 + 11), 2);
            subPacketBeginIndex = 7 + 11;
        }

        List<BitsPacket> subPackets = new ArrayList<>();
        return new BitsOperatorPacket(packetHeader, subPackets);
    }

}
