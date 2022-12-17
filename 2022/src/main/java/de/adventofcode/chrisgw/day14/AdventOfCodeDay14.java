package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
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

    private List<SolidRockStructure> parseSolidRockStructuresFromInput() {
        return inputLines().map(SolidRockStructure::parseSolidRockStructure).toList();
    }


    @Override
    public Integer solveFirstPart() {
        while (true) {
            Coordinate coordinate = spawnSandAndDropIt();
            sandCoordinates.add(coordinate);
            if (isInEndlessVoid(coordinate)) {
                sandCoordinates.remove(coordinate);
                return sandCoordinates.size();
            }
        }
    }

    private boolean isInEndlessVoid(Coordinate sandCoordinates) {
        return sandCoordinates.y() > lowestRockStructureLevel;
    }


    @Override
    public Integer solveSecondPart() {
        while (true) {
            Coordinate coordinate = spawnSandAndDropIt();
            sandCoordinates.add(coordinate);
            if (isAtSandSpawner(coordinate)) {
                return sandCoordinates.size();
            }
        }
    }

    private boolean isAtSandSpawner(Coordinate coordinate) {
        return sandSpawnerCoordinate.equals(coordinate);
    }


    private Coordinate spawnSandAndDropIt() {
        AtomicInteger currentLevel = new AtomicInteger(sandSpawnerCoordinate.y() - 1);
        return Stream.iterate(sandSpawnerCoordinate, this::dropSand)
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
