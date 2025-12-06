package de.adventofcode.chrisgw.day03;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <a href="https://adventofcode.com/2015/day/3">Advent of Code - day 3</a>
 */
public class AdventOfCodeDay03 extends AdventOfCodePuzzleSolver {

    private int santaIndex = 0;
    private List<DirectedSanta> santaList = new ArrayList<>();
    private Map<Integer, Set<Integer>> visitedPositions = new HashMap<>();


    public AdventOfCodeDay03(List<String> inputLines) {
        super(Year.of(2015), 3, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        return countVisitedHouses(1);
    }

    @Override
    public Integer solveSecondPart() {
        return countVisitedHouses(2);
    }

    private int countVisitedHouses(int santaCount) {
        visitedPositions = new HashMap<>();
        santaList = Stream.generate(DirectedSanta::new).limit(santaCount).toList();
        saveVisitedPosition(0, 0);
        inputDirections().forEachOrdered(this::processDirection);
        return countVisitedPositions();
    }


    private Stream<Direction> inputDirections() {
        String inputLine = getInputLines().getFirst();
        return IntStream.range(0, inputLine.length())
                .mapToObj(i -> Direction.fromSign(inputLine.charAt(i)));
    }


    private void processDirection(Direction direction) {
        DirectedSanta santa = santaList.get(santaIndex++);
        santaIndex %= santaList.size();
        santa.walk(direction);
        saveVisitedPosition(santa.x(), santa.y());
    }

    private void saveVisitedPosition(int x, int y) {
        visitedPositions.computeIfAbsent(x, _ -> new HashSet<>()).add(y);
    }


    private int countVisitedPositions() {
        return visitedPositions.values()
                .stream()
                .mapToInt(Collection::size)
                .sum();
    }

}
