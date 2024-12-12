package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Year;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


/**
 * <a href="https://adventofcode.com/2024/day/10">Advent of Code - day 10</a>
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzleSolver {

    private final MapPosition[][] map;


    public AdventOfCodeDay10(List<String> inputLines) {
        super(Year.of(2024), 10, inputLines);
        map = parseHeightMap();
    }

    private MapPosition[][] parseHeightMap() {
        int mapHeight = getInputLines().size();
        MapPosition[][] parsedMap = new MapPosition[mapHeight][];

        for (int y = 0; y < mapHeight; y++) {
            String line = getInputLines().get(y);
            parsedMap[y] = new MapPosition[line.length()];

            for (int x = 0; x < line.length(); x++) {
                int height = Character.getNumericValue(line.charAt(x));
                var mapPosition = new MapPosition(x, y, height);
                parsedMap[y][x] = mapPosition;
            }
        }
        return parsedMap;
    }


    @Override
    public Integer solveFirstPart() {
        return trailHeads()
                .mapToInt(MapPosition::score)
                .sum();
    }

    @Override
    public Integer solveSecondPart() {
        return trailHeads()
                .mapToInt(MapPosition::rating)
                .sum();
    }

    private Stream<MapPosition> trailHeads() {
        return Arrays.stream(map)
                .flatMap(Arrays::stream)
                .filter(MapPosition::isTrailHead);
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
            long distinctMountainPeakCount = traverseMountainTrails().distinct().count();
            return Math.toIntExact(distinctMountainPeakCount);
        }

        public int rating() {
            long mountainTrailCount = traverseMountainTrails().count();
            return Math.toIntExact(mountainTrailCount);
        }

        private Stream<MapPosition> traverseMountainTrails() {
            Deque<MapPosition> positions = new ArrayDeque<>();
            positions.offer(this);

            Builder<MapPosition> reachableMountainPeaks = Stream.builder();
            while (!positions.isEmpty()) {
                MapPosition position = positions.pop();
                if (position.isMountainPeak()) {
                    reachableMountainPeaks.add(position);
                } else {
                    position.connectedNeighbours().forEach(positions::offer);
                }
            }
            return reachableMountainPeaks.build();
        }


        public MapPosition neighbourInDirection(MapDirection direction) {
            int neighbourX = this.x + direction.dx;
            int neighbourY = this.y + direction.dy;
            if (0 > neighbourY || neighbourY >= map.length || 0 > neighbourX || neighbourX >= map[neighbourY].length) {
                return new MapPosition(neighbourX, neighbourY, '.');
            }
            return map[neighbourY][neighbourX];
        }

        public Stream<MapPosition> connectedNeighbours() {
            return Arrays.stream(MapDirection.values())
                    .map(this::neighbourInDirection)
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
            return "(%2d;%2d) = %d".formatted(x, y, height);
        }

    }

}
