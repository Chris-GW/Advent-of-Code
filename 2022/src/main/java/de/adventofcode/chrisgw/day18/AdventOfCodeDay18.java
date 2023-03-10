package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


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
            long coveredSides = lavaDroplet.neighborLavaDroplets().filter(lavaDroplets::contains).count();
            exposedSides -= coveredSides;
        }

        return exposedSides;
    }

    public Integer solveSecondPart() {
        Set<LavaDroplet> lavaDroplets = inputAsLavaDroplets();
        int minX = lavaDroplets.stream().mapToInt(LavaDroplet::x).min().orElse(0) - 1;
        int minY = lavaDroplets.stream().mapToInt(LavaDroplet::y).min().orElse(0) - 1;
        int minZ = lavaDroplets.stream().mapToInt(LavaDroplet::z).min().orElse(0) - 1;

        int maxX = lavaDroplets.stream().mapToInt(LavaDroplet::x).max().orElse(0) + 1;
        int maxY = lavaDroplets.stream().mapToInt(LavaDroplet::y).max().orElse(0) + 1;
        int maxZ = lavaDroplets.stream().mapToInt(LavaDroplet::z).max().orElse(0) + 1;

        var startPosition = new LavaDroplet(minX, minY, minZ);
        Queue<LavaDroplet> nextPositions = new ArrayDeque<>();
        nextPositions.add(startPosition);

        Set<LavaDroplet> visitedPositions = new HashSet<>(lavaDroplets);
        visitedPositions.add(startPosition);

        reachablePositions(nextPositions, visitedPositions, position -> {
            boolean xIsInside = minX <= position.x() && position.x() <= maxX;
            return xIsInside //
                    && minY <= position.y() && position.y() <= maxY //
                    && minZ <= position.z() && position.z() <= maxZ;
        });

        Set<LavaDroplet> trappedAirPockets = new HashSet<>();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    var position = new LavaDroplet(x, y, z);
                    if (!visitedPositions.contains(position)) {
                        trappedAirPockets.add(position);
                    }
                }
            }
        }

        int exposedSides = lavaDroplets.size() * 6;
        Predicate<LavaDroplet> isCoveredSide = lavaDroplets::contains;
        isCoveredSide = isCoveredSide.or(trappedAirPockets::contains);
        for (LavaDroplet lavaDroplet : lavaDroplets) {
            long coveredSides = lavaDroplet.neighborLavaDroplets().filter(isCoveredSide).count();
            exposedSides -= coveredSides;
        }
        return exposedSides;
    }

    private void reachablePositions(Queue<LavaDroplet> nextPositions, Set<LavaDroplet> visitedPositions,
            Predicate<LavaDroplet> shouldSearchPosition) {
        while (!nextPositions.isEmpty()) {
            nextPositions.poll()
                    .neighborLavaDroplets()
                    .filter(shouldSearchPosition)
                    .filter(visitedPositions::add)
                    .forEach(nextPositions::add);
        }
    }


    private Set<LavaDroplet> inputAsLavaDroplets() {
        return inputLines().map(LavaDroplet::parseLavaDroplet).collect(Collectors.toSet());
    }


    public record LavaDroplet(int x, int y, int z) {

        public static LavaDroplet parseLavaDroplet(String positionStr) {
            String[] split = positionStr.split(",");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int z = Integer.parseInt(split[2]);
            return new LavaDroplet(x, y, z);
        }


        public Stream<LavaDroplet> neighborLavaDroplets() {
            Builder<LavaDroplet> neighborLavaDroplets = Stream.builder();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        int distance = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
                        if (distance != 1) {
                            continue;
                        }
                        int x = this.x() + dx;
                        int y = this.y() + dy;
                        int z = this.z() + dz;
                        var neighborLavaDroplet = new LavaDroplet(x, y, z);
                        neighborLavaDroplets.add(neighborLavaDroplet);
                    }
                }
            }
            return neighborLavaDroplets.build();
        }

    }


}
