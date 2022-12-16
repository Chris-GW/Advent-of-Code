package de.adventofcode.chrisgw.day13;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignalPacket {

    @Getter
    private SignalPacket parent;
    @Getter
    private final List<SignalPacket> children = new ArrayList<>();
    @Getter
    private final int data;


    public SignalPacket() {
        this(-1);
    }

    public SignalPacket(int data) {
        this.data = data;
    }


    public void addChild(SignalPacket child) {
        child.parent = this;
        children.add(child);
    }

    public void addData(int data) {
        addChild(new SignalPacket(data));
    }


    public int getLevel() {
        if (this.isRoot()) {
            return 0;
        } else {
            return parent.getLevel() + 1;
        }
    }


    public boolean isLeaf() {
        return children.isEmpty() && data != -1;
    }

    public boolean isRoot() {
        return parent == null;
    }


    public Stream<Integer> dataStream() {
        if (isLeaf()) {
            return Stream.of(data);
        }
        return children.stream().flatMap(SignalPacket::dataStream);
    }


    @Override
    public String toString() {
        if (isLeaf()) {
            return String.valueOf(data);
        }
        return children.stream().map(SignalPacket::toString).collect(Collectors.joining(",", "[", "]"));
    }

}
