package de.adventofcode.chrisgw.day10;

import lombok.Data;

import java.util.Objects;
import java.util.stream.Stream;


@Data
public class AsteroidLineOfSight {

    private final AsteroidMapLocation fromLocation;
    private final AsteroidMapLocation toLocation;


    public Stream<AsteroidMapLocation> blockedLocations() {
        return Stream.iterate(fromLocation, Objects::nonNull, this::nextLocationAlong)
                .dropWhile(location -> fromLocation.distanceTo(location) <= distance());
    }


    public int distance() {
        return fromLocation.distanceTo(toLocation);
    }


    private AsteroidMapLocation nextLocationAlong(AsteroidMapLocation location) {
        int dx = toLocation.getX() - fromLocation.getX();
        int dy = toLocation.getY() - fromLocation.getY();
        if (dx == 0) {
            dy /= Math.abs(dy);
        } else if (dy == 0) {
            dx /= Math.abs(dx);
        } else {
            int gcd = Math.abs(greatestCommonDivisor(dx, dy));
            dx /= gcd;
            dy /= gcd;
        }

        int x = location.getX() + dx;
        int y = location.getY() + dy;
        return location.getAsteriodMap().asteroidAt(x, y);
    }


    public static int greatestCommonDivisor(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return greatestCommonDivisor(n2, n1 % n2);
    }

}
