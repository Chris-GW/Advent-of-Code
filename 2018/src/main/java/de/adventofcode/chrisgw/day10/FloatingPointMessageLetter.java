package de.adventofcode.chrisgw.day10;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.function.ToIntFunction;


@Data
public class FloatingPointMessageLetter {

    private FloatingPoint startFloatingPoint;
    private Set<FloatingPoint> connectedFloatingPoints = new HashSet<>(42);


    public FloatingPointMessageLetter(FloatingPoint startFloatingPoint) {
        this.startFloatingPoint = startFloatingPoint;
        this.connectedFloatingPoints.add(startFloatingPoint);
    }


    public boolean isValidLetter() {
        return isValidLetterSize() && isValidLetterHeight() && isValidLetterWidth();
    }

    private boolean isValidLetterSize() {
        int letterSize = letterSize();
        return 8 <= letterSize && letterSize <= 60;
    }

    private boolean isValidLetterHeight() {
        int letterHeight = letterHeight();
        return 8 <= letterHeight && letterHeight <= 12;
    }

    private boolean isValidLetterWidth() {
        int letterWidth = letterWidth();
        return 1 <= letterWidth && letterWidth <= 8;
    }


    public int letterSize() {
        return connectedFloatingPoints.size();
    }

    public int letterWidth() {
        return coordinateDifference(FloatingPoint::getX);
    }

    public int letterHeight() {
        return coordinateDifference(FloatingPoint::getY);
    }

    private int coordinateDifference(ToIntFunction<? super FloatingPoint> toIntMapper) {
        int min = min(toIntMapper);
        int max = max(toIntMapper);
        return Math.abs(max - min);
    }

    private int max(ToIntFunction<? super FloatingPoint> toIntMapper) {
        return connectedFloatingPoints.stream().mapToInt(toIntMapper).max().orElse(0);
    }

    private int min(ToIntFunction<? super FloatingPoint> toIntMapper) {
        return connectedFloatingPoints.stream().mapToInt(toIntMapper).min().orElse(0);
    }


    public void addConnectedFloatingPoint(FloatingPoint floatingPoint) {
        if (!isConnectedFloatingPoint(floatingPoint)) {
            throw new IllegalArgumentException("FloatingPoint must be connected to letter");
        }
        connectedFloatingPoints.add(floatingPoint);
    }

    public boolean isConnectedFloatingPoint(FloatingPoint floatingPoint) {
        return connectedFloatingPoints.stream().anyMatch(floatingPoint::isConnectedTo);
    }


    public FloatingPoint topLeft() {
        int minX = min(FloatingPoint::getX);
        int minY = min(FloatingPoint::getY);
        return new FloatingPoint(minX, minY, 0, 0);
    }

    public FloatingPoint bottomRight() {
        int maxX = max(FloatingPoint::getX);
        int maxY = max(FloatingPoint::getY);
        return new FloatingPoint(maxX, maxY, 0, 0);
    }


    public boolean hasFloatingPointAt(int x, int y) {
        return connectedFloatingPoints.stream().anyMatch(floatingPoint -> floatingPoint.isAt(x, y));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        FloatingPoint topLeft = topLeft();
        FloatingPoint bottomRight = bottomRight();

        for (int y = topLeft.getY(); y <= bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x <= bottomRight.getX(); x++) {
                if (hasFloatingPointAt(x, y)) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length() - 1); // delete last "\n"
        return sb.toString();
    }

}
