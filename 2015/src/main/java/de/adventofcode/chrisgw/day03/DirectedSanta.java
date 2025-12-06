package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.day03.AdventOfCodeDay03.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class DirectedSanta {

    private int x = 0;
    private int y = 0;
    private Map<Integer, Map<Integer, Integer>> visitedPositions = new HashMap<>();


    public DirectedSanta() {
        savePosition();
    }


    public void walkDirection(Direction direction) {
        x += direction.dx();
        y += direction.dy();
        savePosition();
    }

    private int savePosition() {
        return visitedPositions
                .computeIfAbsent(x, k -> new HashMap<>())
                .merge(y, 1, Integer::sum);
    }


    public int countVisitedHouses() {
        return visitedPositions.values()
                .stream()
                .map(Map::keySet)
                .mapToInt(Set::size)
                .sum();
    }

}