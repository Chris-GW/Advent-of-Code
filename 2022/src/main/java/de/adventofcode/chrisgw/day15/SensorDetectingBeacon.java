package de.adventofcode.chrisgw.day15;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public record SensorDetectingBeacon(Coordinate sensorCoordinate, Coordinate beaconCoordinate) {


    public static SensorDetectingBeacon parseSensorDetectingBeacon(String line) {
        Pattern pattern = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("expect line to match " + pattern + ", but was: " + line);
        }
        int xSensor = Integer.parseInt(matcher.group(1));
        int ySensor = Integer.parseInt(matcher.group(2));
        int xBeacon = Integer.parseInt(matcher.group(3));
        int yBeacon = Integer.parseInt(matcher.group(4));
        return new SensorDetectingBeacon(new Coordinate(xSensor, ySensor), new Coordinate(xBeacon, yBeacon));
    }


    public boolean coversCoordinate(Coordinate coordinate) {
        return sensorCoordinate().distance(coordinate) <= beaconDistance();
    }


    public int beaconDistance() {
        return sensorCoordinate().distance(beaconCoordinate());
    }

    public Stream<Coordinate> outerCoordinate() {
        int beaconDistance = beaconDistance();
        Coordinate sensorCoordinate = sensorCoordinate();
        return Stream.of(
                new Coordinate(sensorCoordinate.x() - beaconDistance, sensorCoordinate.y()),
                new Coordinate(sensorCoordinate.x() + beaconDistance, sensorCoordinate.y()),
                new Coordinate(sensorCoordinate.x(), sensorCoordinate.y() - beaconDistance),
                new Coordinate(sensorCoordinate.x(), sensorCoordinate.y() + beaconDistance));
    }

}
