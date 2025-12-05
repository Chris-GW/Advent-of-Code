package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Long solveSecondPart() {
        List<IdRange> idRanges = inputLines()
                .takeWhile(Predicate.not(String::isBlank))
                .map(IdRange::parse)
                .toList();

        while (true) {
            List<IdRange> finalIdRanges = idRanges;
            Optional<IdRange> current = idRanges.stream()
                    .filter(idRange -> finalIdRanges.stream().anyMatch(idRange::canMerge))
                    .findAny();
            if (current.isPresent()) {
                idRanges = merge(idRanges, current.get());
            } else {
                break;
            }
        }
        return idRanges.stream().mapToLong(IdRange::length).sum();
    }

    private List<IdRange> merge(List<IdRange> idRanges, IdRange current) {
        List<IdRange> newIdRanges = new ArrayList<>();
        for (IdRange other : idRanges) {
            if (current.canMerge(other)) {
                IdRange merged = current.merge(other);
                newIdRanges.add(merged);
            } else if (current != other) {
                newIdRanges.add(other);
            }
        }
        return newIdRanges;
    }


    public record IdRange(long startId, long endId) {

        public static IdRange parse(String line) {
            String[] split = line.split("-");
            long startId = Long.parseLong(split[0]);
            long endId = Long.parseLong(split[1]);
            if (startId < 0 || endId < 0) {
                throw new IllegalArgumentException("asasd");
            }
            return new IdRange(startId, endId);
        }


        public boolean canMerge(IdRange other) {
            return this != other && (contains(other.startId) || contains(other.endId));
        }


        public IdRange merge(IdRange other) {
            if (!canMerge(other)) {
                throw new IllegalArgumentException("Could not merge with " + other);
            }
            long startId = Math.min(this.startId, other.startId);
            long endId = Math.max(this.endId, other.endId);
            return new IdRange(startId, endId);
        }


        public boolean contains(long id) {
            return startId <= id && id <= endId;
        }


        public long length() {
            return endId - startId + 1;
        }


        @Override
        public String toString() {
            return "%d-%d".formatted(startId, endId);
        }

    }

}
