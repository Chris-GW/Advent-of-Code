package de.adventofcode.chrisgw.day10;

import lombok.Data;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
public class AsteroidMapLocation {

    private final AdventOfCodeDay10 asteriodMap;
    private final int x;
    private final int y;
    private final boolean asteroid;


    public long visibleAsteroidCount() {
        return visibleAsteroids().count();
    }

    public Stream<AsteroidMapLocation> visibleAsteroids() {
        Set<AsteroidMapLocation> blockedAsteroidLocations = lineOfSights().flatMap(
                AsteroidLineOfSight::blockedLocations).collect(Collectors.toSet());
        blockedAsteroidLocations.add(this);
        return asteriodMap.asteroidLocations().filter(Predicate.not(blockedAsteroidLocations::contains));
    }

    public Stream<AsteroidLineOfSight> lineOfSights() {
        return asteriodMap.asteroidLocations()
                .filter(Predicate.not(this::equals))
                .map(asteroidLocation -> new AsteroidLineOfSight(this, asteroidLocation));
    }


    public int distanceTo(AsteroidMapLocation otherLocation) {
        int distanceX = Math.abs(this.x - otherLocation.x);
        int distanceY = Math.abs(this.y - otherLocation.y);
        return distanceX + distanceY;
    }


    @Override
    public String toString() {
        return String.format("(%2d,%2d)%s", getX(), getY(), isAsteroid() ? "#" : ".");
    }

}
