package de.adventofcode.chrisgw.day05;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.joining;


public record HydrothermalVentsLine(Position2D startPosition, Position2D endPosition) {

    public static Pattern hydrothermalVentsLinePattern = Pattern.compile("(\\d+),(\\d+)\\s->\\s(\\d+),(\\d+)");


    public static HydrothermalVentsLine parseHydrothermalVentsLine(String line) {
        Matcher matcher = hydrothermalVentsLinePattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expect line to match pattern '%s', but was: %s".formatted(hydrothermalVentsLinePattern, line));
        }
        int x1 = parseInt(matcher.group(1));
        int y1 = parseInt(matcher.group(2));
        int x2 = parseInt(matcher.group(3));
        int y2 = parseInt(matcher.group(4));
        return new HydrothermalVentsLine(new Position2D(x1, y1), new Position2D(x2, y2));
    }


    public boolean isHorizontalLine() {
        return startPosition.y() == endPosition.y();
    }

    public boolean isVerticalLine() {
        return startPosition.x() == endPosition.x();
    }

    public boolean isDiagonalLine() {
        return !(isHorizontalLine() || isVerticalLine());
    }


    public Stream<Position2D> positions() {
        var positions = Stream.iterate(startPosition, Predicate.not(endPosition::equals), position -> {
            int dx = 0;
            int dy = 0;
            if (position.x() < endPosition.x()) {
                dx = 1;
            } else if (position.x() > endPosition.x()) {
                dx = -1;
            }
            if (position.y() < endPosition.y()) {
                dy = 1;
            } else if (position.y() > endPosition.y()) {
                dy = -1;
            }
            return position.move(dx, dy);
        });
        return Stream.concat(positions, Stream.of(endPosition));
    }

    public boolean coverPosition(Position2D position) {
        return positions().anyMatch(position::equals);
    }


    @Override
    public String toString() {
        String positionsStr = positions().map(Position2D::toString).collect(joining(" | "));
        return startPosition + " -> " + endPosition + " covers: " + positionsStr;
    }

}
