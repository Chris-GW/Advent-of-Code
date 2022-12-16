package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * <a href="https://adventofcode.com/2022/day/13">Advent of Code - day 13</a>
 */
public class AdventOfCodeDay13 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay13(List<String> inputLines) {
        super(Year.of(2022), 13, inputLines);
    }


    public Integer solveFirstPart() {
        List<SignalPacket> signalPackets = parseSignalPacketsFromInput();

        int sum = 0;
        for (int i = 0; i < signalPackets.size(); i++) {
            int packetIndex = i / 2 + 1;
            SignalPacket leftPacket = signalPackets.get(i);
            SignalPacket rightPacket = signalPackets.get(++i);
            int compare = packetComparator().compare(leftPacket, rightPacket);
            if (compare < 0) {
                sum += packetIndex;
            }
        }
        return sum;
    }

    public Integer solveSecondPart() {
        List<SignalPacket> signalPackets = new ArrayList<>(parseSignalPacketsFromInput());
        SignalPacket firstDividerPacket = SignalPacket.parseSignalPacket("[[2]]");
        SignalPacket secondDividerPacket = SignalPacket.parseSignalPacket("[[6]]");

        signalPackets.add(firstDividerPacket);
        signalPackets.add(secondDividerPacket);
        signalPackets.sort(packetComparator());

        int firstDividerPacketIndex = signalPackets.indexOf(firstDividerPacket) + 1;
        int secondDividerPacketIndex = signalPackets.indexOf(secondDividerPacket) + 1;
        return firstDividerPacketIndex * secondDividerPacketIndex;
    }

    private List<SignalPacket> parseSignalPacketsFromInput() {
        return inputLines()
                .filter(StringUtils::isNoneBlank)
                .map(SignalPacket::parseSignalPacket)
                .toList();
    }


    private static Comparator<SignalPacket> packetComparator() {
        return new Comparator<>() {
            @Override
            public int compare(SignalPacket leftPacket, SignalPacket rightPacket) {
                if (leftPacket.isLeaf() && rightPacket.isLeaf()) {
                    int leftData = leftPacket.getData();
                    int rightData = rightPacket.getData();
                    return Integer.compare(leftData, rightData);

                } else if (!leftPacket.isLeaf() && !rightPacket.isLeaf()) {
                    List<SignalPacket> leftChildren = leftPacket.getChildren();
                    List<SignalPacket> rightChildren = rightPacket.getChildren();
                    for (int i = 0; i < leftChildren.size() && i < rightChildren.size(); i++) {
                        SignalPacket leftChild = leftChildren.get(i);
                        SignalPacket rightChild = rightChildren.get(i);
                        int compare = compare(leftChild, rightChild);
                        if (compare != 0) {
                            return compare;
                        }
                    }
                    return Integer.compare(leftChildren.size(), rightChildren.size());

                } else if (leftPacket.isLeaf()) {
                    SignalPacket packet = new SignalPacket();
                    packet.addData(leftPacket.getData());
                    return compare(packet, rightPacket);

                } else if (rightPacket.isLeaf()) {
                    SignalPacket packet = new SignalPacket();
                    packet.addData(rightPacket.getData());
                    return compare(leftPacket, packet);
                }
                return 0;
            }
        };
    }


}
