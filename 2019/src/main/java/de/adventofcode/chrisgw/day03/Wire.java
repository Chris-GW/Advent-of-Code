package de.adventofcode.chrisgw.day03;

import lombok.Data;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Data
public class Wire implements Iterable<GridPoint> {

    private Map<GridPoint, Integer> wirePath = new LinkedHashMap<>();

    public static Wire parseWirePath(GridPoint startingPoint, String wirePathStr) {
        Wire wire = new Wire();

        List<GridDirection> wirePathDirections = Pattern.compile(",")
                .splitAsStream(wirePathStr)
                .map(GridDirection::parse)
                .collect(Collectors.toList());

        int distance = 0;
        wire.wirePath.put(startingPoint, distance++);
        GridPoint currentPoint = startingPoint;
        for (GridDirection wirePathDirection : wirePathDirections) {
            List<GridPoint> wirePath = wirePathDirection.pathFrom(currentPoint);
            for (GridPoint point : wirePath) {
                wire.wirePath.putIfAbsent(point, distance++);
            }
            currentPoint = wirePath.get(wirePath.size() - 1);
        }
        return wire;
    }

    public Set<GridPoint> intersectionPointsWith(Wire otherWire) {
        Set<GridPoint> intersectionPoints = new HashSet<>(intersectionPoints());
        intersectionPoints.retainAll(otherWire.intersectionPoints());
        return intersectionPoints;
    }


    public int signalDistanceTo(GridPoint intersectionPoint) {
        return wirePath.getOrDefault(intersectionPoint, Integer.MAX_VALUE);
    }


    public Set<GridPoint> intersectionPoints() {
        return wirePath.keySet();
    }


    @Override
    public Iterator<GridPoint> iterator() {
        return intersectionPoints().iterator();
    }

}
