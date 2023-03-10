package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * <a href="https://adventofcode.com/2022/day/18">Advent of Code - day 18</a>
 */
public class AdventOfCodeDay18 extends AdventOfCodePuzzleSolver {


    public AdventOfCodeDay18(List<String> inputLines) {
        super(Year.of(2022), 18, inputLines);
    }


    public Integer solveFirstPart() {
        Set<LavaDroplet> lavaDroplets = inputAsLavaDroplets();
        int exposedSides = lavaDroplets.size() * 6;

        for (LavaDroplet lavaDroplet : lavaDroplets) {
            long coveredSides = lavaDroplet.neighbors().filter(lavaDroplets::contains).count();
            exposedSides -= coveredSides;
        }

        return exposedSides;
    }

    // part 2

    public Integer solveSecondPart() {
        Set<LavaDroplet> lavaDroplets = inputAsLavaDroplets();
        Set<LavaDroplet> trappedAirPockets = findTrappedAirPockets(lavaDroplets);

        int exposedSides = lavaDroplets.size() * 6;
        Predicate<LavaDroplet> isCoveredSide = lavaDroplets::contains;
        Predicate<LavaDroplet> isTrappedAirPocket = trappedAirPockets::contains;
        for (LavaDroplet lavaDroplet : lavaDroplets) {
            long coveredSides = lavaDroplet.neighbors().filter(isCoveredSide.or(isTrappedAirPocket)).count();
            exposedSides -= coveredSides;
        }
        return exposedSides;
    }


    private Set<LavaDroplet> findTrappedAirPockets(Set<LavaDroplet> lavaDroplets) {
        int minX = lavaDroplets.stream().mapToInt(LavaDroplet::x).min().orElse(0) - 1;
        int minY = lavaDroplets.stream().mapToInt(LavaDroplet::y).min().orElse(0) - 1;
        int minZ = lavaDroplets.stream().mapToInt(LavaDroplet::z).min().orElse(0) - 1;

        int maxX = lavaDroplets.stream().mapToInt(LavaDroplet::x).max().orElse(0) + 1;
        int maxY = lavaDroplets.stream().mapToInt(LavaDroplet::y).max().orElse(0) + 1;
        int maxZ = lavaDroplets.stream().mapToInt(LavaDroplet::z).max().orElse(0) + 1;

        Set<LavaDroplet> unvisitedPositions = new HashSet<>();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    var position = new LavaDroplet(x, y, z);
                    unvisitedPositions.add(position);
                }
            }
        }

        var startPosition = new LavaDroplet(minX, minY, minZ);
        Queue<LavaDroplet> nextPositions = new ArrayDeque<>();
        nextPositions.add(startPosition);
        unvisitedPositions.remove(startPosition);
        unvisitedPositions.removeAll(lavaDroplets);

        Predicate<LavaDroplet> isInsideArea = position -> {
            boolean xIsInside = minX <= position.x() && position.x() <= maxX;
            return xIsInside //
                    && minY <= position.y() && position.y() <= maxY //
                    && minZ <= position.z() && position.z() <= maxZ;
        };

        reachablePositions(nextPositions, unvisitedPositions, isInsideArea);
        return unvisitedPositions;
    }

    private void reachablePositions(Queue<LavaDroplet> nextPositions, Set<LavaDroplet> unvisitedPositions,
            Predicate<LavaDroplet> shouldSearchPosition) {
        while (!nextPositions.isEmpty()) {
            nextPositions.poll()
                    .neighbors()
                    .filter(shouldSearchPosition)
                    .filter(unvisitedPositions::remove)
                    .forEach(nextPositions::add);
        }
    }


    private Set<LavaDroplet> inputAsLavaDroplets() {
        return inputLines().map(LavaDroplet::parseLavaDroplet).collect(Collectors.toSet());
    }


}
