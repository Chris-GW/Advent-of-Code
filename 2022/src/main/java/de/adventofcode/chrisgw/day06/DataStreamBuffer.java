package de.adventofcode.chrisgw.day06;

public record DataStreamBuffer(String dataStream) {


    public static final int START_OF_PACKET_MARKER_LENGTH = 4;
    public static final int START_OF_MESSAGE_MARKER_LENGTH = 14;


    public int findStartOfPacketMarker() {
        return firstDistinctCharSequenceOfLength(START_OF_PACKET_MARKER_LENGTH);
    }

    public int findStartOfMessageMarker() {
        return firstDistinctCharSequenceOfLength(START_OF_MESSAGE_MARKER_LENGTH);
    }


    private int firstDistinctCharSequenceOfLength(int packetLength) {
        StringBuilder dataBuffer = new StringBuilder(packetLength);
        for (int i = 0; i + packetLength < dataStream.length(); i++) {
            dataBuffer.setLength(0);
            dataBuffer.insert(0, dataStream, i, i + packetLength);
            if (hasDistinctCharacters(dataBuffer)) {
                return i + packetLength;
            }
        }
        return -1;
    }

    private static boolean hasDistinctCharacters(CharSequence packetData) {
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
