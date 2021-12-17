package de.adventofcode.chrisgw.day05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
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

    public boolean isHorizontalOrVerticalLine() {
        return isHorizontalLine() || isVerticalLine();
    }

    public boolean isHorizontalLine() {
        return startPosition.y() == endPosition.y();
    }

    public boolean isVerticalLine() {
        return startPosition.x() == endPosition.x();
    }


    public int length() {
        if (isHorizontalLine()) {
            return Math.abs(startPosition.x() - endPosition.x());
        } else {
            return Math.abs(startPosition.y() - endPosition.y());
        }
    }


    public Stream<Position2D> positions() {
        if (isHorizontalLine()) {
            int startX = Math.min(startPosition.x(), endPosition.x());
            int endX = Math.max(startPosition.x(), endPosition.x());
            return IntStream.rangeClosed(startX, endX).mapToObj(x -> new Position2D(x, startPosition.y()));
        } else {
            int startY = Math.min(startPosition.y(), endPosition.y());
            int endY = Math.max(startPosition.y(), endPosition.y());
            return IntStream.rangeClosed(startY, endY).mapToObj(y -> new Position2D(startPosition.x(), y));
        }
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
