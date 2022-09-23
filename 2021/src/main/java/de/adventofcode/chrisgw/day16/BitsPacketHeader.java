package de.adventofcode.chrisgw.day16;

public record BitsPacketHeader(int version, int typeId) {

    public static final int HEADER_PACKET_SIZE = 3;

    public static BitsPacketHeader parseHeader(String binaryString) {
        return parseHeader(binaryString, 0);
    }

    public static BitsPacketHeader parseHeader(String binaryString, int beginIndex) {
        int version = parseHeaderPacket(binaryString, beginIndex);
        beginIndex += HEADER_PACKET_SIZE;
        int typeId = parseHeaderPacket(binaryString, beginIndex);
        return new BitsPacketHeader(version, typeId);
    }

    private static int parseHeaderPacket(String binaryString, int beginIndex) {
        return Integer.parseInt(binaryString, beginIndex, beginIndex + HEADER_PACKET_SIZE, 2);
    }

}
