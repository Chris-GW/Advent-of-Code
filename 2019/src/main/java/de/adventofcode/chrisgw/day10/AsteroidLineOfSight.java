package de.adventofcode.chrisgw.day10;

import lombok.Data;

import java.util.Objects;
import java.util.stream.Stream;


@Data
public class AsteroidLineOfSight {

    private final AsteroidMapLocation fromLocation;
    private final int dx;
    private final int dy;
    private final AsteroidMapLocation toLocation;


    public static AsteroidLineOfSight between(AsteroidMapLocation fromLocation, AsteroidMapLocation toLocation) {
        int dx = toLocation.getX() - fromLocation.getX();
        int dy = toLocation.getY() - fromLocation.getY();
        int gcd = Math.abs(greatestCommonDivisor(dx, dy));
        dx /= gcd;
        dy /= gcd;
        return new AsteroidLineOfSight(fromLocation, dx, dy, toLocation);
    }


    public Stream<AsteroidMapLocation> blockedLocations() {
        return Stream.iterate(toLocation, Objects::nonNull, this::nextLocationAlong).skip(1);
    }


    private AsteroidMapLocation nextLocationAlong(AsteroidMapLocation location) {
        int x = location.getX() + dx;
        int y = location.getY() + dy;
        return location.getAsteriodMap().asteroidAt(x, y);
    }


    public double winkel() {
        double degrees = Math.toDegrees(winkel(dx, dy));
        degrees -= 180;
        if (degrees < 0) {
            degrees += 360;
        }
        return degrees;
    }


    public static int greatestCommonDivisor(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return greatestCommonDivisor(n2, n1 % n2);
    }


    public static double winkel(int x2, int y2) {
        int x1 = 0;
        int y1 = 1;
        int dot = x1 * x2 + y1 * y2;
        int det = x1 * y2 - y1 * x2;
        return Math.atan2(det, dot);
    }

}
