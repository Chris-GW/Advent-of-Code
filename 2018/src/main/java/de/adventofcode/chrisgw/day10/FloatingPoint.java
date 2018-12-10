package de.adventofcode.chrisgw.day10;

import lombok.Value;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Value
public class FloatingPoint {

    public static final Pattern FLOATING_POINT_NOTE_PATTERN = Pattern.compile(
            "position=<\\s*(-?\\d+),\\s*(-?\\d+)>\\s+velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>", Pattern.CASE_INSENSITIVE);

    private final int x;
    private final int y;

    private final int dx;
    private final int dy;


    public static FloatingPoint parseFloatingPointNote(String floatingPointNote) {
        Matcher floatingPointNoteMatcher = FLOATING_POINT_NOTE_PATTERN.matcher(floatingPointNote);
        if (!floatingPointNoteMatcher.matches()) {
            throw new IllegalArgumentException(
                    "Given floating point note doesn't match expected pattern: " + FLOATING_POINT_NOTE_PATTERN);
        }
        int x = Integer.parseInt(floatingPointNoteMatcher.group(1));
        int y = Integer.parseInt(floatingPointNoteMatcher.group(2));
        int dx = Integer.parseInt(floatingPointNoteMatcher.group(3));
        int dy = Integer.parseInt(floatingPointNoteMatcher.group(4));
        return new FloatingPoint(x, y, dx, dy);
    }


    public FloatingPoint moveFor(Duration duration) {
        if (duration.isZero()) {
            return this;
        }
        int seconds = (int) TheStarsAlign.toSeconds(duration);
        int x = this.x + dx * seconds;
        int y = this.y + dy * seconds;
        return new FloatingPoint(x, y, dx, dy);
    }


    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean isConnectedTo(FloatingPoint otherFloatingPoint) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int x = this.x + dx;
                int y = this.y + dy;
                if (otherFloatingPoint.isAt(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

}
