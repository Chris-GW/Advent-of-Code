package de.adventofcode.chrisgw.day06;

public record DataStreamBuffer(String dataStream) {


    public static final int START_OF_PACKET_LENGTH = 4;

    public int findFirstStartOfPacketIndex() {
        StringBuilder dataBuffer = new StringBuilder(4);
        for (int i = 0; i + START_OF_PACKET_LENGTH < dataStream.length(); i++) {
            dataBuffer.setLength(0);
            dataBuffer.insert(0, dataStream, i, i + START_OF_PACKET_LENGTH);
            if (isStartOfPacket(dataBuffer)) {
                return i + START_OF_PACKET_LENGTH;
            }
        }
        return -1;
    }

    public boolean isStartOfPacket(CharSequence packetData) {
        for (int i = 1; i < packetData.length(); i++) {
            char dataLetter = packetData.charAt(i);
            for (int j = i - 1; j >= 0; j--) {
                char previousDataLetter = packetData.charAt(j);
                if (dataLetter == previousDataLetter) {
                    return false;
                }
            }
        }
        return true;
    }

}
