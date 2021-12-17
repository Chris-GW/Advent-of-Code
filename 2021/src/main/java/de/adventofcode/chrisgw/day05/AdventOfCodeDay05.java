package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


/**
 * https://adventofcode.com/2021/day/5
 */
public class AdventOfCodeDay05 extends AdventOfCodePuzzleSolver<Long> {

    public AdventOfCodeDay05(List<String> inputLines) {
        super(Year.of(2021), 5, inputLines);
    }


    public Long solveFirstPart() {
        List<HydrothermalVentsLine> hydrothermalVentsLines = parseHydrothermalVentsLines();
        Map<Position2D, Long> coveredPositions = hydrothermalVentsLines.stream()
                .filter(Predicate.not(HydrothermalVentsLine::isDiagonalLine))
                .flatMap(HydrothermalVentsLine::positions)
                .collect(groupingBy(Function.identity(), counting()));
        return coveredPositions.values().stream().filter(count -> count > 1).count();
    }


    public Long solveSecondPart() {
        List<HydrothermalVentsLine> hydrothermalVentsLines = parseHydrothermalVentsLines();
        Map<Position2D, Long> coveredPositions = hydrothermalVentsLines.stream()
                .flatMap(HydrothermalVentsLine::positions)
                .collect(groupingBy(Function.identity(), counting()));
        return coveredPositions.values().stream().filter(count -> count > 1).count();
    }


    private List<HydrothermalVentsLine> parseHydrothermalVentsLines() {
        return inputLines().map(HydrothermalVentsLine::parseHydrothermalVentsLine).toList();
    }


    @Override
    public String toString() {
        Map<Position2D, Long> coveredPositions = parseHydrothermalVentsLines().stream()
                .flatMap(HydrothermalVentsLine::positions)
                .collect(groupingBy(Function.identity(), counting()));
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                long count = coveredPositions.getOrDefault(new Position2D(x, y), 0L);
                if (count > 0) {
                    sb.append(count);
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
