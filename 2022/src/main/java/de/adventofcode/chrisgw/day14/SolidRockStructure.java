package de.adventofcode.chrisgw.day14;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolidRockStructure {

    @Getter
    private final Set<Coordinate> coordinates = new HashSet<>();


    public static SolidRockStructure parseSolidRockStructure(String structureLines) {
        var solidRockStructure = new SolidRockStructure();
        Pattern pattern = Pattern.compile("(?: -> )?(\\d+),(\\d+)");
        Matcher matcher = pattern.matcher(structureLines);

        Coordinate lineStartCoordinate = null;
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            Coordinate coordinate = new Coordinate(x, y);
            if (lineStartCoordinate != null) {
                List<Coordinate> lineCoordinates = lineStartCoordinate.lineTo(coordinate);
                solidRockStructure.coordinates.addAll(lineCoordinates);
            }
            lineStartCoordinate = coordinate;
        }
        return solidRockStructure;
    }


    public boolean containsCoordinate(Coordinate coordinate) {
        return coordinates.contains(coordinate);
    }

}
