package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * <a href="https://adventofcode.com/2022/day/15">Advent of Code - day 15</a>
 */
public class AdventOfCodeDay15 extends AdventOfCodePuzzleSolver {

    private int reviewedRow;

    public AdventOfCodeDay15(List<String> inputLines) {
        super(Year.of(2022), 15, inputLines);
    }


    public Integer solveFirstPart() {
        return countCoveredCoordinatesInRow();
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

    public AdventOfCodeDay15 withReviewedRow(int reviewedRow) {
        this.reviewedRow = reviewedRow;
        return this;
    }


    private int countCoveredCoordinatesInRow() {
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

}
