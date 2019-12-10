package de.adventofcode.chrisgw.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 2019 Day 10: Monitoring Station
 * https://adventofcode.com/2019/day/10
 */
public class AdventOfCodeDay10 {

    private AsteroidMapLocation[][] asteroidMap;


    public AdventOfCodeDay10(List<String> asteroidMapStr) {
        int width = asteroidMapStr.get(0).length();
        int height = asteroidMapStr.size();
        this.asteroidMap = new AsteroidMapLocation[height][width];
        for (int y = 0; y < height; y++) {
            String asteroidRowStr = asteroidMapStr.get(y);
            this.asteroidMap[y] = new AsteroidMapLocation[width];
            for (int x = 0; x < width; x++) {
                boolean isAsteroid = asteroidRowStr.charAt(x) == '#';
                AsteroidMapLocation mapLocation = new AsteroidMapLocation(this, x, y);
                mapLocation.setAsteroid(isAsteroid);
                this.asteroidMap[y][x] = mapLocation;
            }
        }
    }


    public AsteroidMapLocation findBestMonitoringStationLocation() {
        return asteroidLocations().max(Comparator.comparingLong(AsteroidMapLocation::visibleAsteroidCount))
                .orElseThrow();
    }


    public List<AsteroidMapLocation> vaporizedAsteroids() {
        AsteroidMapLocation lazerLocation = findBestMonitoringStationLocation();

        List<AsteroidMapLocation> vaporizedAsteroids = new ArrayList<>(200);
        while (asteroidLocations().skip(1).findAny().isPresent()) {
            List<AsteroidMapLocation> newVaporizedAsteroids = fireLazer360(lazerLocation);
            newVaporizedAsteroids.forEach(asteroid -> asteroid.setAsteroid(false));
            vaporizedAsteroids.addAll(newVaporizedAsteroids);
        }
        return vaporizedAsteroids;
    }


    private List<AsteroidMapLocation> fireLazer360(AsteroidMapLocation lazerLocation) {
        return lazerLocation.visibleAsteroids()
                .map(visibleAsteroidLocation -> AsteroidLineOfSight.between(lazerLocation, visibleAsteroidLocation))
                .sorted(Comparator.comparingDouble(AsteroidLineOfSight::winkel))
                .map(AsteroidLineOfSight::getToLocation)
                .collect(Collectors.toList());
    }


    public Stream<AsteroidMapLocation> mapLocations() {
        return Arrays.stream(asteroidMap).flatMap(Arrays::stream);
    }

    public Stream<AsteroidMapLocation> asteroidLocations() {
        return mapLocations().filter(AsteroidMapLocation::isAsteroid);
    }

    public AsteroidMapLocation asteroidAt(int x, int y) {
        if (!isLocationOnMap(x, y)) {
            return null;
        }
        return asteroidMap[y][x];
    }

    private boolean isLocationOnMap(int x, int y) {
        boolean isValidY = 0 <= y && y < width();
        return isValidY && 0 <= x && x < height();
    }


    public int width() {
        return asteroidMap.length;
    }

    public int height() {
        return asteroidMap[0].length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < width(); y++) {
            for (int x = 0; x < height(); x++) {
                AsteroidMapLocation mapLocation = asteroidAt(x, y);
                if (mapLocation.isAsteroid()) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
