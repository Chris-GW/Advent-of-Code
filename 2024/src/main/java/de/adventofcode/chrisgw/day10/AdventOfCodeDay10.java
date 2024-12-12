package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2024/day/10">Advent of Code - day 10</a>
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzleSolver {

    private MapPosition[][] map;


    public AdventOfCodeDay10(List<String> inputLines) {
        super(Year.of(2024), 10, inputLines);
    }

    private void parseHeightMap() {
        int mapHeight = getInputLines().size();
        map = new MapPosition[mapHeight][];

        for (int y = 0; y < mapHeight; y++) {
            String line = getInputLines().get(y);
            map[y] = new MapPosition[line.length()];

            for (int x = 0; x < line.length(); x++) {
                int height = Character.getNumericValue(line.charAt(x));
                var mapPosition = new MapPosition(x, y, height);
                map[y][x] = mapPosition;
            }
        }
    }


    @Override
    public Integer solveFirstPart() {
        parseHeightMap();
        List<MapPosition> trailHeads = trailHeads().toList();
        return trailHeads
                .stream()
                .mapToInt(MapPosition::score)
                .sum();
    }

    private Stream<MapPosition> trailHeads() {
        return Arrays.stream(map)
                .flatMap(Arrays::stream)
                .filter(MapPosition::isTrailHead);
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private MapPosition mapPositionAt(int x, int y) {
        if (0 > y || y >= map.length || 0 > x || x >= map[y].length) {
            return new MapPosition(x, y, Integer.MAX_VALUE);
        }
        return map[y][x];
    }


    class MapPosition {

        final int x;
        final int y;
        final int height;


        MapPosition(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }


        public boolean isTrailHead() {
            return height == 0;
        }

        public boolean isMountainPeak() {
            return height == 9;
        }


        public int score() {
            Set<MapPosition> reachableMountainPeaks = new HashSet<>();
            Deque<MapPosition> positions = new ArrayDeque<>();
            positions.offer(this);

            while (!positions.isEmpty()) {
                MapPosition position = positions.pop();
                if (position.isMountainPeak()) {
                    reachableMountainPeaks.add(position);
                } else {
                    List<MapPosition> trailPositions = position.connectedTrailPositions().toList();
                    trailPositions.forEach(positions::offer);
                }
            }
            return reachableMountainPeaks.size();
        }


        public MapPosition positionInDirection(Direction direction) {
            int x = this.x + direction.dx;
            int y = this.y + direction.dy;
            return mapPositionAt(x, y);
        }

        public Stream<MapPosition> connectedTrailPositions() {
            List<MapPosition> neighbors = Arrays.stream(Direction.values())
                    .map(this::positionInDirection)
                    .toList();
            return neighbors
                    .stream()
                    .filter(mapPosition -> this.height + 1 == mapPosition.height);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MapPosition position)) {
                return false;
            }
            return new EqualsBuilder().append(x, position.x)
                    .append(y, position.y)
                    .append(height, position.height)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(x).append(y).append(height).toHashCode();
        }

        @Override
        public String toString() {
            return "(%2d;%2d)=%d".formatted(x, y, height);
        }
    }


    enum Direction {
        UP(0, -1),
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0);

        final int dx;
        final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

    }

}
