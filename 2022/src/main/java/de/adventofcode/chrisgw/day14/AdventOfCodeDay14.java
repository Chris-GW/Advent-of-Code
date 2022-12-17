package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day14.Coordinate.*;


/**
 * <a href="https://adventofcode.com/2022/day/14">Advent of Code - day 14</a>
 */
public class AdventOfCodeDay14 extends AdventOfCodePuzzleSolver {

    private final Coordinate sandSpawnerCoordinate = new Coordinate(500, 0);
    private final int lowestRockStructureLevel;
    private final Set<Coordinate> rockCoordinates;
    private final Set<Coordinate> sandCoordinates = new HashSet<>();

    public AdventOfCodeDay14(List<String> inputLines) {
        super(Year.of(2022), 14, inputLines);
        this.rockCoordinates = parseSolidRockStructuresFromInput()
                .stream()
                .map(SolidRockStructure::getCoordinates)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        this.lowestRockStructureLevel = rockCoordinates.stream()
                .mapToInt(Coordinate::y)
                .max()
                .orElse(sandSpawnerCoordinate.y());
    }


    @Override
    public Integer solveFirstPart() {
        for (int sandCounter = 0; true; sandCounter++) {
            Coordinate coordinate = spawnSandAndDropIt(this::isNotInEndlessVoid);
            sandCoordinates.add(coordinate);
            if (coordinate == null) {
                return sandCounter;
            }
        }
    }

    @Override
    public Integer solveSecondPart() {
        for (int sandCounter = 0; true; sandCounter++) {
            Coordinate coordinate = spawnSandAndDropIt(coordinate1 -> true);
            sandCoordinates.add(coordinate);
            if (sandSpawnerCoordinate.equals(coordinate)) {
                return sandCounter + 1;
            }
        }
    }

    private boolean isNotAtSandSpawner(Coordinate coordinate) {
        return !sandSpawnerCoordinate.equals(coordinate);
    }

    private List<SolidRockStructure> parseSolidRockStructuresFromInput() {
        return inputLines().map(SolidRockStructure::parseSolidRockStructure).toList();
    }


    private Coordinate spawnSandAndDropIt(Predicate<Coordinate> endSandDropPredicate) {
        AtomicInteger currentLevel = new AtomicInteger(sandSpawnerCoordinate.y() - 1);
        return Stream.iterate(sandSpawnerCoordinate, endSandDropPredicate, this::dropSand)
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
        return sandCoordinates.y() <= lowestRockStructureLevel;
    }


    public boolean isRockAt(Coordinate coordinate) {
        return rockCoordinates.contains(coordinate);
    }

    public boolean isSandAt(Coordinate coordinate) {
        return sandCoordinates.contains(coordinate);
    }

    public boolean isFloor(Coordinate coordinate) {
        return coordinate.y() == 2 + lowestRockStructureLevel;
    }

    public boolean isAirAt(Coordinate coordinate) {
        return !isRockAt(coordinate) && !isSandAt(coordinate) && !isFloor(coordinate);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y <= lowestRockStructureLevel + 3; y++) {
            for (int x = 490; x <= 505; x++) {
                var coordinate = new Coordinate(x, y);
                if (sandSpawnerCoordinate.equals(coordinate)) {
                    sb.append('+');
                } else if (isRockAt(coordinate) || isFloor(coordinate)) {
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
