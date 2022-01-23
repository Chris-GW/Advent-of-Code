package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

/**
 * Day 12: Passage Pathing
 * https://adventofcode.com/2021/day/12
 */
public class AdventOfCodeDay12 extends AdventOfCodePuzzleSolver<Integer> {

    private final Map<String, Cave> caveNameMap;


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
        return findCave(Cave.START_CAVE_NAME);
    }


    public Integer solveFirstPart() {
        var cavePath = new CavePath(startCave());
        return toIntExact(buildPossibleCavePaths(cavePath).count());
    }

    public Integer solveSecondPart() {
        var cavePath = new CavePathWithTwiceSmallCave(startCave());
        return toIntExact(buildPossibleCavePaths(cavePath).count());
    }


    private Stream<CavePath> buildPossibleCavePaths(CavePath cavePath) {
        if (cavePath.isComplete()) {
            return Stream.of(cavePath);
        }
        return cavePath.appendableCaves()
                .map(cavePath::append)
                .flatMap(this::buildPossibleCavePaths);
    }

}
