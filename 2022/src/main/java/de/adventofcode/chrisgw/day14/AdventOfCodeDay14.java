package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day14.Coordinate.*;


/**
 * <a href="https://adventofcode.com/2022/day/14">Advent of Code - day 14</a>
 */
public class AdventOfCodeDay14 extends AdventOfCodePuzzleSolver {

    private final Coordinate sandSpawnerCoordinate = new Coordinate(500, 0);
    private final Set<Coordinate> rockCoordinates;
    private final Set<Coordinate> sandCoordinates = new HashSet<>();

    public AdventOfCodeDay14(List<String> inputLines) {
        super(Year.of(2022), 14, inputLines);
        this.rockCoordinates = parseSolidRockStructuresFromInput()
                .stream()
                .map(SolidRockStructure::getCoordinates)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Integer solveFirstPart() {
        for (int sandCounter = 0; true; sandCounter++) {
            Coordinate coordinate = spawnSandAndDropIt();
            if (coordinate == null) {
                return sandCounter;
            }
            sandCoordinates.add(coordinate);
        }
    }

    private Coordinate spawnSandAndDropIt() {
        AtomicInteger currentLevel = new AtomicInteger(sandSpawnerCoordinate.y() - 1);
        return Stream.iterate(sandSpawnerCoordinate, this::isNotInEndlessVoid, this::dropSand)
                .dropWhile(coordinate -> coordinate.y() > currentLevel.getAndIncrement())
                .findFirst()
                .orElse(null);
    }

    private Coordinate dropSand(Coordinate sandCoordinate) {
        return Stream.of(STEP_DOWN, STEP_DOWN_LEFT, STEP_DOWN_RIGHT)
                .map(sandCoordinate::add)
                .filter(this::isAirAt)
                .findFirst()
                .orElse(sandCoordinate);
    }

    private boolean isNotInEndlessVoid(Coordinate sandCoordinates) {
        int voidLevel = rockCoordinates.stream()
                .mapToInt(Coordinate::x)
                .max()
                .orElse(sandSpawnerCoordinate.y());
        return sandCoordinates.y() <= voidLevel;
    }

    private List<SolidRockStructure> parseSolidRockStructuresFromInput() {
        return inputLines().map(SolidRockStructure::parseSolidRockStructure).toList();
    }

    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    public boolean isRockAt(Coordinate coordinate) {
        return rockCoordinates.contains(coordinate);
    }

    public boolean isSandAt(Coordinate coordinate) {
        return sandCoordinates.contains(coordinate);
    }

    public boolean isAirAt(Coordinate coordinate) {
        return !isRockAt(coordinate) && !isSandAt(coordinate);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y <= 9; y++) {
            for (int x = 494; x <= 503; x++) {
                var coordinate = new Coordinate(x, y);
                if (sandSpawnerCoordinate.equals(coordinate)) {
                    sb.append('+');
                } else if (isRockAt(coordinate)) {
                    sb.append('#');
                } else if (isSandAt(coordinate)) {
                    sb.append('o');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


}
