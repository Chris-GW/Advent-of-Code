package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2023/day/8">Advent of Code - day 8</a>
 */
public class AdventOfCodeDay08 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay08(List<String> inputLines) {
        super(Year.of(2023), 8, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<String> inputLines = getInputLines();
        String directionLine = inputLines.get(0);
        List<Direction> directions = directionLine.chars()
                .mapToObj(Character::toString)
                .map(Direction::valueOf)
                .toList();

        Map<String, NetworkRecord> networkMap = inputLines.stream()
                .skip(2)
                .map(NetworkRecord::parseNetworkRecord)
                .collect(Collectors.toMap(NetworkRecord::startNode, Function.identity()));

        String startNode = "AAA";
        String targetNode = "ZZZ";
        String currentNode = startNode;

        for (int step = 0; true; step++) {
            if (currentNode.equals(targetNode)) {
                return step;
            }
            Direction direction = directions.get(step % directions.size());
            currentNode = networkMap.get(currentNode).nodeInDirection(direction);
        }
    }


    @Override
    public Integer solveSecondPart() {
        List<String> inputLines = getInputLines();
        String directionLine = inputLines.get(0);
        List<Direction> directions = directionLine.chars()
                .mapToObj(Character::toString)
                .map(Direction::valueOf)
                .toList();

        Map<String, NetworkRecord> networkMap = inputLines.stream()
                .skip(2)
                .map(NetworkRecord::parseNetworkRecord)
                .collect(Collectors.toMap(NetworkRecord::startNode, Function.identity()));

        List<String> currentNodes = networkMap.keySet()
                .stream()
                .filter(nodeLabel -> nodeLabel.endsWith("A"))
                .toList();

        for (int step = 0; true; step++) {
            if (currentNodes.stream().allMatch(nodeLabel -> nodeLabel.endsWith("Z"))) {
                return step;
            }
            Direction direction = directions.get(step % directions.size());
            currentNodes = currentNodes.stream()
                    .map(networkMap::get)
                    .map(networkRecord -> networkRecord.nodeInDirection(direction))
                    .toList();
        }
    }


    public record NetworkRecord(String startNode, String leftNode, String rightNode) {

        public static final Pattern NETWORK_RECORD_PATTERN = Pattern.compile("(\\w{3}) = \\((\\w{3}), (\\w{3})\\)");

        public static NetworkRecord parseNetworkRecord(String networkRecordLine) {
            Matcher matcher = NETWORK_RECORD_PATTERN.matcher(networkRecordLine);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("expect network record line matching pattern "
                        + NETWORK_RECORD_PATTERN + ", but was: " + networkRecordLine);
            }
            String startingNodeLabel = matcher.group(1);
            String leftNodeLabel = matcher.group(2);
            String rightNodeLabel = matcher.group(3);
            return new NetworkRecord(startingNodeLabel, leftNodeLabel, rightNodeLabel);
        }


        public String nodeInDirection(Direction direction) {
            return switch (direction) {
                case L -> leftNode();
                case R -> rightNode();
            };
        }


        @Override
        public String toString() {
            return "%s = (%s, %s)".formatted(startNode(), leftNode(), rightNode());
        }

    }

    public enum Direction {
        L, R

    }

}
