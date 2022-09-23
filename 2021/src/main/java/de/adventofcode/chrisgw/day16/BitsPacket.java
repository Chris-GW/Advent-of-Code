package de.adventofcode.chrisgw.day16;

import java.util.ArrayList;
import java.util.List;


public abstract class BitsPacket {

    private final BitsPacketHeader packetHeader;
    private final List<BitsPacket> subPackets;


    protected BitsPacket(BitsPacketHeader packetHeader, List<BitsPacket> subPackets) {
        this.packetHeader = packetHeader;
        this.subPackets = new ArrayList<>(subPackets);
    }


    public int version() {
        return packetHeader().version();
    }

    public int typeId() {
        return packetHeader().typeId();
    }

    private BitsPacketHeader packetHeader() {
        return packetHeader;
    }


    public int versionSum() {
        return version() + subPackets.stream().mapToInt(BitsPacket::versionSum).sum();
    }


}
