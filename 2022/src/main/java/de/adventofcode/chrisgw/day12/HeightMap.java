package de.adventofcode.chrisgw.day12;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class HeightMap {

    private final HeightMapLocation[][] heightMap;
    @Getter
    private final HeightMapLocation startLocation;

    @Getter
    private final HeightMapLocation bestSignalLocation;

    private HeightMap(HeightMapLocation[][] heightMap) {
        this.heightMap = heightMap;
        this.startLocation = locations()
                .filter(HeightMapLocation::isStartLocation)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("could not find startLocation"));
        this.bestSignalLocation = locations()
                .filter(HeightMapLocation::isBestSignalLocation)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("could not find bestSignalLocation"));
        ;
    }

    public static HeightMap parseHeightMap(List<String> inputLines) {
        HeightMapLocation[][] heightMap = new HeightMapLocation[inputLines.size()][];
        for (int y = 0; y < inputLines.size(); y++) {
            String heightMapRow = inputLines.get(y);
            heightMap[y] = new HeightMapLocation[heightMapRow.length()];

            for (int x = 0; x < heightMapRow.length(); x++) {
                char elevationCode = heightMapRow.charAt(x);
                heightMap[y][x] = new HeightMapLocation(x, y, elevationCode);
            }
        }
        return new HeightMap(heightMap);
    }


    public Stream<HeightMapLocation> locations() {
        return Arrays.stream(heightMap).flatMap(Arrays::stream);
    }


    public HeightMapLocation locationAt(int x, int y) {
        if (0 <= y && y < heightMap.length) {
            HeightMapLocation[] heightMapRow = heightMap[y];
            if (0 <= x && x < heightMapRow.length) {
                return heightMapRow[x];
            }
        }
        return null;
    }


    public Stream<HeightMapLocation> possibleNextLocations(HeightMapLocation fromLocation) {
        return Arrays.stream(StepDirection.values()).map(direction -> {
            int x = fromLocation.x() + direction.getDx();
            int y = fromLocation.y() + direction.getDy();
            return locationAt(x, y);
        }).filter(fromLocation::canClimbTo);
    }

}
