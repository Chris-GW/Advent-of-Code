package de.adventofcode.chrisgw.day09;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class HeightMap {

    private HeightMapLocation[][] locations;


    private HeightMap() {
    }

    public static HeightMap parseHeightMap(List<String> mapLines) {
        HeightMap heightMap = new HeightMap();
        heightMap.locations = new HeightMapLocation[mapLines.size()][];
        for (int y = 0; y < mapLines.size(); y++) {
            String line = mapLines.get(y);
            heightMap.locations[y] = new HeightMapLocation[line.length()];
            for (int x = 0; x < line.length(); x++) {
                int height = line.charAt(x) - '0';
                heightMap.locations[y][x] = heightMap.new HeightMapLocation(x, y, height);
            }
        }
        return heightMap;
    }


    public HeightMapLocation locationAt(int x, int y) {
        if (containsLocation(x, y)) {
            return locations[y][x];
        }
        return null;
    }

    public boolean containsLocation(int x, int y) {
        return 0 <= y && y < locations.length &&
                0 <= x && x < locations[y].length;
    }

    public Stream<HeightMapLocation> findLowestPoints() {
        return Arrays.stream(locations)
                .flatMap(Arrays::stream)
                .filter(HeightMapLocation::isLowestPoint);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                HeightMapLocation heightMapLocation = locationAt(x, y);
                if (heightMapLocation.isLowestPoint()) {
                    sb.append("#");
                } else {
                    sb.append(heightMapLocation.getHeight());
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    @Data
    public class HeightMapLocation implements Comparable<HeightMapLocation> {

        private final int x;
        private final int y;
        private final int height;


        public Stream<HeightMapLocation> adjacentLocations() {
            return Arrays.stream(Direction.values())
                    .map(this::locationInDirection)
                    .filter(Objects::nonNull);
        }

        public HeightMapLocation locationInDirection(Direction direction) {
            return locationAt(x + direction.dx(), y + direction.dy());
        }


        public boolean isLowestPoint() {
            return adjacentLocations().allMatch(this::isLower);
        }

        public boolean isLower(HeightMapLocation otherMapLocation) {
            return this.compareTo(otherMapLocation) < 0;
        }


        public int riskLevel() {
            return 1 + height;
        }


        @Override
        public int compareTo(HeightMapLocation otherLocation) {
            return Integer.compare(this.height, otherLocation.height);
        }

        @Override
        public String toString() {
            return "[%2d;%2d]=%1d".formatted(x, y, height);
        }

    }

}
