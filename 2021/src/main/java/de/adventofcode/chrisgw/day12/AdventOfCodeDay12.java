package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * Day 12: Passage Pathing
 * https://adventofcode.com/2021/day/12
 */
public class AdventOfCodeDay12 extends AdventOfCodePuzzleSolver<Integer> {

    public static final String START_CAVE_NAME = "start";
    public static final String END_CAVE_NAME = "end";
    public final Map<String, Cave> caveNameMap;


    public AdventOfCodeDay12(List<String> inputLines) {
        super(Year.of(2021), 12, inputLines);
        caveNameMap = parseCaveNetwork(inputLines);
    }

    private Map<String, Cave> parseCaveNetwork(List<String> inputLines) {
        Map<String, Cave> caveNameMap = new HashMap<>();

        for (String caveEntry : inputLines) {
            String[] splitCaveEntry = caveEntry.split("-");
            Cave cave = caveNameMap.computeIfAbsent(splitCaveEntry[0], Cave::new);
            Cave otherCave = caveNameMap.computeIfAbsent(splitCaveEntry[1], Cave::new);
            cave.connect(otherCave);
        }
        return caveNameMap;
    }


    public Cave findCave(String name) {
        return caveNameMap.get(name);
    }

    public Cave startCave() {
        return findCave(START_CAVE_NAME);
    }

    public Cave endCave() {
        return findCave(END_CAVE_NAME);
    }


    public Integer solveFirstPart() {
        CavePath cavePath = new CavePath().append(startCave());
        Set<CavePath> possibleCavePaths = buildPossibleCavePaths(cavePath).collect(Collectors.toSet());
        return possibleCavePaths.size();
    }

    private Stream<CavePath> buildPossibleCavePaths(CavePath cavePath) {
        if (cavePath.getLast().equals(endCave())) {
            return Stream.of(cavePath);
        }
        return cavePath.getLast()
                .connectedCaves()
                .filter(cavePath::canAppend)
                .map(cavePath::append)
                .flatMap(this::buildPossibleCavePaths);
    }

    public Integer solveSecondPart() {
        //TODO solveSecondPart
        return 0;
    }


    @Data
    public static class Cave {

        private final String name;

        @EqualsAndHashCode.Exclude
        private final Set<Cave> connectedCaves = new HashSet<>();


        public boolean connect(Cave otherCave) {
            if (connectedCaves.add(otherCave)) {
                otherCave.connect(this);
                return true;
            }
            return false;
        }

        public boolean isBigCave() {
            return StringUtils.isAllUpperCase(name);
        }

        public Stream<Cave> connectedCaves() {
            return connectedCaves.stream();
        }


        @Override
        public String toString() {
            return name;
        }

    }


    @Data
    public static class CavePath implements Iterable<Cave> {

        private final Deque<Cave> cavePath = new ArrayDeque<>();


        public boolean canAppend(Cave cave) {
            return cave != null && cave.isBigCave() || !containsCave(cave);
        }

        public boolean containsCave(Cave cave) {
            return cavePath.contains(cave);
        }


        public Cave getFirst() {
            return cavePath.getFirst();
        }

        public Cave getLast() {
            return cavePath.getLast();
        }

        public CavePath append(Cave cave) {
            if (!canAppend(cave)) {
                throw new IllegalArgumentException("can't append cave: " + cave);
            }
            CavePath cavePath = new CavePath();
            cavePath.cavePath.addAll(this.cavePath);
            cavePath.cavePath.add(cave);
            return cavePath;
        }


        @Override
        public Iterator<Cave> iterator() {
            return cavePath.iterator();
        }

        @Override
        public String toString() {
            return cavePath.stream().map(Cave::getName).collect(joining(","));
        }

    }

}
