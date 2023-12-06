package de.adventofcode.chrisgw.day06;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


/**
 * <a href="https://adventofcode.com/2023/day/6">Advent of Code - day 6</a>
 */
public class AdventOfCodeDay06 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay06(List<String> inputLines) {
        super(Year.of(2023), 6, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<ToyBoatRace> races = parseToyBoatRaces(getInputLines());
        return races.stream()
                .mapToInt(ToyBoatRace::countPossibleWaysOfWinning)
                .reduce((left, right) -> left * right)
                .orElse(0);
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    private List<ToyBoatRace> parseToyBoatRaces(List<String> inputLines) {
        int[] raceTimes = new int[0];
        int[] recordDistances = new int[0];
        for (String line : inputLines) {
            if (line.startsWith("Time:")) {
                raceTimes = Arrays.stream(line.substring("Time:".length()).trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } else if (line.startsWith("Distance:")) {
                recordDistances = Arrays.stream(line.substring("Distance:".length()).trim().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } else {
                throw new IllegalArgumentException("could not parse: " + line);
            }
        }
        if (raceTimes.length != recordDistances.length) {
            throw new IllegalArgumentException(
                    "expect same size for raceTimes and recordDistances, but was: %d and %d".formatted( //
                            raceTimes.length, recordDistances.length));
        }

        List<ToyBoatRace> races = new ArrayList<>(raceTimes.length);
        for (int i = 0; i < raceTimes.length; i++) {
            int raceDuration = raceTimes[i];
            int recordDistance = recordDistances[i];
            races.add(new ToyBoatRace(raceDuration, recordDistance));
        }
        return races;
    }


    public record ToyBoatRace(int raceDuration, int recordDistance) {

        public int countPossibleWaysOfWinning() {
            return Math.toIntExact(IntStream.range(1, raceDuration).filter(this::isWinningCharge).count());
        }

        private boolean isWinningCharge(int chargeDuration) {
            int movingDuration = raceDuration - chargeDuration;
            int distance = movingDuration * chargeDuration;
            return distance > recordDistance;
        }

    }

}
