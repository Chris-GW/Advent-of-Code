package de.adventofcode.chrisgw.day17;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class VerticalChamber {

    @Getter
    private final int width;


    private int jetIndex = 0;
    private final List<JetOfHotGas> jetsInChamber;

    private final List<FallingRock> fallingRocks = new ArrayList<>();
    private FallingRock currentFallingRock;


    public VerticalChamber(int width, List<JetOfHotGas> jetsInChamber) {
        this.width = width;
        this.jetsInChamber = new ArrayList<>(jetsInChamber);
    }


    public FallingRock nextFallingRock() {
        currentFallingRock = spawnFallingRock();
        while (true) {
            var jetOfHotGas = jetsInChamber.get(jetIndex);
            jetIndex = (jetIndex + 1) % jetsInChamber.size();

            currentFallingRock.moveRockRelative(jetOfHotGas.horizontalPushForce(), 0);
            boolean isFalling = currentFallingRock.moveRockRelative(0, -1);
            if (!isFalling) {
                break;
            }
        }
        fallingRocks.add(currentFallingRock);
        return currentFallingRock;
    }


    private FallingRock spawnFallingRock() {
        RockShape[] rockShapes = RockShape.values();
        int shapeIndex = fallingRocks.size() % rockShapes.length;
        RockShape rockShape = rockShapes[shapeIndex];
        int rockTowerHeight = rockTowerHeight();
        int y = rockTowerHeight + 4;
        RockCoordinate spawnCoordinate = new RockCoordinate(2, y);
        return new FallingRock(spawnCoordinate, rockShape);
    }


    public boolean isRockAt(RockCoordinate coordinate) {
        return fallingRocks.stream().anyMatch(fallingRock -> fallingRock.isRockAt(coordinate));
    }

    public boolean isInsideChamber(RockCoordinate coordinate) {
        boolean insideWidth = 0 <= coordinate.x() && coordinate.x() < width;
        return insideWidth && 0 < coordinate.y();
    }


    public int rockTowerHeight() {
        return fallingRocks.stream().flatMap(FallingRock::rockCoordinates).mapToInt(RockCoordinate::y).max().orElse(0);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int height = Math.max(rockTowerHeight(),
                currentFallingRock.rockCoordinates().mapToInt(RockCoordinate::y).max().orElse(0));
        for (int row = height; row > 0; row--) {
            for (int column = 0; column < width; column++) {
                if (column == 0) {
                    sb.append('|');
                }
                var coordinate = new RockCoordinate(column, row);
                if (currentFallingRock.isRockAt(coordinate)) {
                    sb.append('@');
                } else if (isRockAt(coordinate)) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
                if (column == width - 1) {
                    sb.append('|');
                }
            }
            sb.append("\n");
            if (row == 1) {
                sb.append('+').append(StringUtils.repeat("-", width)).append("+\n");
            }
        }
        return sb.toString();
    }

    public class FallingRock {

        private Set<RockCoordinate> rockCoordinates;


        private FallingRock(RockCoordinate topLeft, RockShape rockShape) {
            rockCoordinates = rockShape.rockCoordinates().collect(Collectors.toSet());
            moveRockRelative(topLeft.x(), topLeft.y());
        }


        public boolean moveRockRelative(int dx, int dy) {
            Set<RockCoordinate> previousRockCoordinates = rockCoordinates;
            rockCoordinates = rockCoordinates.stream()
                    .map(coordinate -> coordinate.moveRelative(dx, dy))
                    .collect(Collectors.toSet());
            if (!isValidRockPosition()) {
                rockCoordinates = previousRockCoordinates;
                return false;
            }
            return true;
        }

        private boolean isValidRockPosition() {
            boolean allInsideChamber = rockCoordinates().allMatch(VerticalChamber.this::isInsideChamber);
            return allInsideChamber && rockCoordinates().noneMatch(VerticalChamber.this::isRockAt);
        }


        public boolean isResting() {

            return false;
        }


        public boolean isRockAt(RockCoordinate coordinate) {
            return rockCoordinates.contains(coordinate);
        }


        public Stream<RockCoordinate> rockCoordinates() {
            return rockCoordinates.stream();
        }

    }

}
