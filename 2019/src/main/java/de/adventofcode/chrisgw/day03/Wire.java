package de.adventofcode.chrisgw.day03;

import lombok.Data;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Data
public class Wire implements Iterable<GridPoint> {

    private Set<GridPoint> wirePath = new LinkedHashSet<>();
    private List<Direction> pathDirections = new ArrayList<>();

    public static Wire parseWirePath(GridPoint startingPoint, String wirePathStr) {
        Wire wire = new Wire();

        List<GridDirection> wirePathDirections = Pattern.compile(",")
                .splitAsStream(wirePathStr)
                .map(GridDirection::parse)
                .collect(Collectors.toList());

        GridPoint currentPoint = startingPoint;
        for (GridDirection wirePathDirection : wirePathDirections) {
            List<GridPoint> wirePath = wirePathDirection.pathFrom(currentPoint);
            wire.wirePath.addAll(wirePath);
            //            Stream.generate(wirePathDirection::getDirection)
            //                    .limit(wirePathDirection.getLength())
            //                    .forEach(wire.pathDirections::add);
            currentPoint = wirePath.get(wirePath.size() - 1);
        }
        return wire;
    }

    public Set<GridPoint> intersectionPointsWith(Wire otherWire) {
        Set<GridPoint> intersectionPoints = new HashSet<>(this.wirePath);
        intersectionPoints.retainAll(otherWire.wirePath);
        return intersectionPoints;
    }


    @Override
    public Iterator<GridPoint> iterator() {
        return wirePath.iterator();
    }

}
