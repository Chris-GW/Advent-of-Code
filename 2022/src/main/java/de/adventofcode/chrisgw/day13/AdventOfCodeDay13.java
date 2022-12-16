package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <a href="https://adventofcode.com/2022/day/13">Advent of Code - day 13</a>
 */
public class AdventOfCodeDay13 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay13(List<String> inputLines) {
        super(Year.of(2022), 13, inputLines);
    }


    public Integer solveFirstPart() {
        int sum = 0;
        int packetIndex = 1;
        List<String> inputLines = getInputLines();
        for (int i = 0; i < inputLines.size(); i++) {
            System.out.printf("== Pair %d ==%n", packetIndex);
            System.out.println(inputLines.get(i));
            System.out.println(inputLines.get(i + 1));
            SignalPacket leftPacket = parseSignalPacket(inputLines.get(i));
            SignalPacket rightPacket = parseSignalPacket(inputLines.get(++i));
            int compare = packetComparator().compare(leftPacket, rightPacket);
            if (compare < 0) {
                sum += packetIndex;
            }
            i++;
            packetIndex++;
        }
        return sum;
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

    private SignalPacket parseSignalPacket(String packetDataLine) {
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
        return new SignalPacket();
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
