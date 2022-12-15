package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.*;


/**
 * <a href="https://adventofcode.com/2022/day/12">Advent of Code - day 12</a>
 */
public class AdventOfCodeDay12 extends AdventOfCodePuzzleSolver {

    private final HeightMap heightMap;


    public AdventOfCodeDay12(List<String> inputLines) {
        super(Year.of(2022), 12, inputLines);
        heightMap = HeightMap.parseHeightMap(inputLines);
    }


    public Integer solveFirstPart() {
        List<HeightMapLocation> heightMapLocations = findPathToBestSignalLocation();
        return heightMapLocations.size() - 1;
    }


    private List<HeightMapLocation> findPathToBestSignalLocation() {
        var start = heightMap.getStartLocation();
        var goal = heightMap.getBestSignalLocation();

        Map<HeightMapLocation, HeightMapLocation> cameFrom = new HashMap<>();
        Map<HeightMapLocation, Integer> gScore = new HashMap<>();
        Map<HeightMapLocation, Integer> fScore = new HashMap<>();
        PriorityQueue<HeightMapLocation> openSet = new PriorityQueue<>(Comparator.comparingInt(otherLocation -> fScore.getOrDefault(otherLocation, Integer.MAX_VALUE)));

        openSet.add(start);
        gScore.put(start, 0);
        fScore.put(start, start.distanceTo(goal));

        while (!openSet.isEmpty()) {
            HeightMapLocation current = openSet.poll();
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            for (HeightMapLocation neighbor : heightMap.possibleNextLocations(current).toList()) {
                int tentative_gScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (tentative_gScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentative_gScore);
                    fScore.put(neighbor, tentative_gScore + neighbor.distanceTo(goal));
                    if (!openSet.contains(current)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        throw new IllegalArgumentException("could not find path");
    }

    private List<HeightMapLocation> reconstructPath(Map<HeightMapLocation, HeightMapLocation> cameFrom, HeightMapLocation current) {
        List<HeightMapLocation> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(current);
        }
        return totalPath;
    }


    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }

}
