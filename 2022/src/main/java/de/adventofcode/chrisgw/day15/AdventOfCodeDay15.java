package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * <a href="https://adventofcode.com/2022/day/15">Advent of Code - day 15</a>
 */
public class AdventOfCodeDay15 extends AdventOfCodePuzzleSolver {

    private int reviewedRow;
    private int maxRange;


    public AdventOfCodeDay15(List<String> inputLines) {
        super(Year.of(2022), 15, inputLines);
    }


    public Integer solveFirstPart() {
        List<SensorDetectingBeacon> sensors = inputLines()
                .map(SensorDetectingBeacon::parseSensorDetectingBeacon)
                .toList();
        Set<Coordinate> beaconCoordinates = sensors.stream()
                .map(SensorDetectingBeacon::beaconCoordinate)
                .collect(Collectors.toSet());

        int xMin = sensors.stream().flatMap(SensorDetectingBeacon::outerCoordinate).mapToInt(Coordinate::x).min().orElse(0);
        int xMax = sensors.stream().flatMap(SensorDetectingBeacon::outerCoordinate).mapToInt(Coordinate::x).max().orElse(0);
        return Math.toIntExact(IntStream.rangeClosed(xMin, xMax)
                .mapToObj(x -> new Coordinate(x, reviewedRow))
                .filter(Predicate.not(beaconCoordinates::contains))
                .filter(coordinate -> sensors.stream().anyMatch(sensorDetectingBeacon -> sensorDetectingBeacon.coversCoordinate(coordinate)))
                .count());
    }

    public AdventOfCodeDay15 withReviewedRow(int reviewedRow) {
        this.reviewedRow = reviewedRow;
        return this;
    }


    public Long solveSecondPart() {
        List<SensorDetectingBeacon> sensors = inputLines()
                .map(SensorDetectingBeacon::parseSensorDetectingBeacon)
                .toList();

        for (int y = 0; y <= maxRange; y++) {
            for (int x = 0; x <= maxRange; x++) {
                int row = y;
                int column = x;
                Optional<SensorDetectingBeacon> foundSensor = sensors.stream()
                        .filter(sensor -> sensor.coversCoordinate(column, row))
                        .findAny();
                if (foundSensor.isEmpty()) {
                    return tuningFrequency(new Coordinate(x, y));
                }
                x = foundSensor.get().rightCoordinate(row);
            }
        }
        throw new IllegalStateException("could not find distress signal");
    }

    private static long tuningFrequency(Coordinate coordinate) {
        return 4_000_000L * coordinate.x() + coordinate.y();
    }

    public AdventOfCodeDay15 withMaxRange(int maxRange) {
        this.maxRange = maxRange;
        return this;
    }

}
