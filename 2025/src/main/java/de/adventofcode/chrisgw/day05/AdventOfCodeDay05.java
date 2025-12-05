package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.Predicate;


/**
 * <a href="https://adventofcode.com/2025/day/5">Advent of Code - day 5</a>
 */
public class AdventOfCodeDay05 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay05(List<String> inputLines) {
        super(Year.of(2025), 5, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<IdRange> idRanges = inputLines()
                .takeWhile(Predicate.not(String::isBlank))
                .map(IdRange::parse)
                .toList();
        return Math.toIntExact(inputLines()
                .skip(idRanges.size())
                .skip(1)
                .mapToLong(Long::parseLong)
                .filter(id -> idRanges.stream().anyMatch(idRange -> idRange.contains(id)))
                .count());
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    public record IdRange(long startId, long endId) {

        public static IdRange parse(String line) {
            String[] split = line.split("-");
            long startId = Long.parseLong(split[0]);
            long endId = Long.parseLong(split[1]);
            return new IdRange(startId, endId);
        }

        public boolean contains(long id) {
            return startId <= id && id <= endId;
        }

    }

}
