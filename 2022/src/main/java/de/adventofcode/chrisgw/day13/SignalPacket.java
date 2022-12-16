package de.adventofcode.chrisgw.day13;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SignalPacket {

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


    public static SignalPacket parseSignalPacket(String packetDataLine) {
        Deque<SignalPacket> packetStack = new ArrayDeque<>();
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher numberMatcher = numberPattern.matcher(packetDataLine);

        for (int i = 0; i < packetDataLine.length(); i++) {
            char nextChar = packetDataLine.charAt(i);
            if (nextChar == '[') {
                packetStack.push(new SignalPacket());
            } else if (nextChar == ']') {
                SignalPacket packet = packetStack.pop();
                if (packetStack.isEmpty()) {
                    return packet;
                } else {
                    packetStack.peek().addChild(packet);
                }

            } else if (nextChar == ',') {
                continue;

            } else if (numberMatcher.find()) {
                int data = Integer.parseInt(numberMatcher.group());
                packetStack.peek().addData(data);
            }
        }
        throw new IllegalArgumentException("could not parse packet: " + packetDataLine);
    }


    public void addChild(SignalPacket child) {
        children.add(child);
    }

    public void addData(int data) {
        addChild(new SignalPacket(data));
    }


    public boolean isLeaf() {
        return children.isEmpty() && data != -1;
    }


    @Override
    public String toString() {
        if (isLeaf()) {
            return String.valueOf(data);
        }
        return children.stream().map(SignalPacket::toString).collect(Collectors.joining(",", "[", "]"));
    }

}
