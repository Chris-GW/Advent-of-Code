package de.adventofcode.chrisgw.day03;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;


/**
 * https://adventofcode.com/2019/day/3
 */
@Data
public class AdventOfCodeDay03 {

    private final GridPoint centralPort = new GridPoint(0, 0);
    private final List<Wire> wires;
    private final Set<GridPoint> intersectionPoints;


    public AdventOfCodeDay03(List<String> wirePaths) {
        wires = wirePaths.stream()
                .map(wirePathStr -> Wire.parseWirePath(centralPort, wirePathStr))
                .collect(Collectors.toList());
        intersectionPoints = findWireIntersectionPoints();
    }

    private Set<GridPoint> findWireIntersectionPoints() {
        Set<GridPoint> intersectionPoints = new HashSet<>();
        for (int i = 0; i < wires.size(); i++) {
            for (int j = i + 1; j < wires.size(); j++) {
                Wire wire = wires.get(i);
                Wire otherWire = wires.get(j);
                intersectionPoints.addAll(wire.intersectionPointsWith(otherWire));
            }
        }
        intersectionPoints.remove(centralPort);
        return intersectionPoints;
    }


    public GridPoint closesIntersectionPoint() {
        return getIntersectionPoints().stream()
                .min(Comparator.comparingInt(centralPort::distanceTo))
                .orElse(centralPort);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        GridPoint topLeft = wires.stream()
                .map(Wire::getWirePath)
                .flatMap(Collection::stream)
                .max(comparingInt(GridPoint::getX).reversed().thenComparingInt(GridPoint::getY))
                .orElse(centralPort);

        GridPoint bottomRight = wires.stream()
                .map(Wire::getWirePath)
                .flatMap(Collection::stream)
                .max(comparingInt(GridPoint::getX).thenComparing(comparingInt(GridPoint::getY).reversed()))
                .orElse(centralPort);

        for (int y = topLeft.getY() + 1; y >= bottomRight.getY() - 1; y--) {
            for (int x = topLeft.getX() - 1; x <= bottomRight.getX() + 1; x++) {
                GridPoint gridPoint = new GridPoint(x, y);
                if (centralPort.isAt(gridPoint)) {
                    sb.append('o');
                } else if (intersectionPoints.contains(gridPoint)) {
                    sb.append('X');
                } else if (isWirePoint(gridPoint)) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean isWirePoint(GridPoint gridPoint) {
        return wires.stream().map(Wire::getWirePath).flatMap(Collection::stream).anyMatch(gridPoint::isAt);
    }


}
